package com.wxc.service.impl;

import com.wxc.dao.PromoMapper;
import com.wxc.entity.Promo;
import com.wxc.service.PromoService;
import com.wxc.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoServiceImpl implements PromoService {
    @Autowired
    PromoMapper promoMapper;
    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        Promo promo = promoMapper.selectByItemId(itemId);
        PromoModel promoModel = convertFromDataObject(promo);
        if(promo==null){
            return null;
        }
        if (promoModel.getStartDate().isBeforeNow()) {
            promoModel.setStatus(1);
        } else if (promoModel.getEndDate().isBeforeNow()) {
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convertFromDataObject(Promo promo) {
        if(promo==null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promo, promoModel);
        promoModel.setStartDate(new DateTime(promo.getStartDate()));
        promoModel.setEndDate(new DateTime(promo.getEndDate()));
        return promoModel;
    }
}
