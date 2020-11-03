package com.wxc.service;

import com.wxc.error.BuinessException;
import com.wxc.service.model.ItemModel;

import java.util.List;

public interface ItemService {
    ItemModel createItem(ItemModel itemModel) throws BuinessException;

    ItemModel getItemById(Integer id);

    List<ItemModel> listItem();

    boolean decreaseStock(Integer itemId,Integer amount);

    void increaseSales(Integer itemId,Integer amount);
}
