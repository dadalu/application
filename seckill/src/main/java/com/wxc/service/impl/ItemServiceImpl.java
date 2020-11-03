package com.wxc.service.impl;

import com.wxc.dao.ItemMapper;
import com.wxc.dao.ItemStockMapper;
import com.wxc.entity.Item;
import com.wxc.entity.ItemStock;
import com.wxc.error.BuinessException;
import com.wxc.error.EmBuinessError;
import com.wxc.service.ItemService;
import com.wxc.service.PromoService;
import com.wxc.service.model.ItemModel;
import com.wxc.service.model.PromoModel;
import com.wxc.validator.ValidationResult;
import com.wxc.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemStockMapper itemStockMapper;
    @Autowired
    private PromoService promoService;
    
    @Override
    public ItemModel createItem(ItemModel itemModel) throws BuinessException {
        ValidationResult result = validator.validate(itemModel);
        if(result.isHasErrors()){
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
        Item item = this.convertItemFromItemModel(itemModel);
        //写入数据库
        itemMapper.insertSelective(item);
        itemModel.setId(item.getId());
        ItemStock itemStock = this.convertItemStockFromItemModel(itemModel);
        itemStockMapper.insertSelective(itemStock);
        return this.getItemById(item.getId());
    }

    private ItemStock convertItemStockFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(itemModel.getId());
        itemStock.setStock(itemModel.getStock());
        return itemStock;
    }

    private Item convertItemFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        Item item = new Item();
        BeanUtils.copyProperties(itemModel, item);
        return item;
    }
    private ItemModel convertModelFromDataObject(Item item, ItemStock itemStockDO) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(item, itemModel);
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }
    @Override
    public ItemModel getItemById(Integer id) {
        Item item = itemMapper.selectByPrimaryKey(id);
        if(item == null){
            return null;
        }
        ItemStock itemStock = itemStockMapper.selectByItemId(item.getId());
        ItemModel itemModel = convertModelFromDataObject(item,itemStock);
        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if(promoModel!=null&&promoModel.getStatus().intValue()!=3){
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    @Override
    public List<ItemModel> listItem() {
        List<Item> itemList = itemMapper.listItem();

        List<ItemModel> itemModelList = itemList.stream().map(item->{
            ItemStock itemStock = itemStockMapper.selectByItemId(item.getId());
            ItemModel itemModel = this.convertModelFromDataObject(item,itemStock);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int affectedRow = itemStockMapper.decreaseStock(itemId,amount);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void increaseSales(Integer itemId, Integer amount) {
        itemMapper.increaseSales(itemId,amount);
    }
}
