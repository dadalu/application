package com.demo;

import java.util.Map;
import java.util.concurrent.*;

public class TestDemo {
    /*v1.PROVINCEZIPAREA provinceziparea,
    v1.ID_OWNER idOwner,
    v1.OWNERNAME ownername,
    v1.ADDRESS address,
    v1.ORGANIZATIONCODE organizationcode,
    v1.PARENTCOMPANY parentcompany,
    v1.ZIPAREA ziparea,
    v1.CORPORATION corporation,
    v1.CORPORATION_TEL corporationTel,
    v1.TEL tel,
    v1.COMPANYSTATE companystate,
    v1.COMPANYSTATE_CODE companystateCode,
    v1.PROPERTY property,
    v1.FIRSTDATE firstdate,
    v1.UPDATETIME updatetime,
    v1.LOG_CHG_TIME logChgTime,
    v1.ID_ROW_SEQ_NO idRowSeqNo
            //
    v2.PROVINCEZIPAREA provinceziparea,
    v2.ID_OWNER id_owner,
    v2.OWNERNAME ownername,
    v2.MGRAREA_CODE mgrarea_code,
    v2.MGRAREA_WORD mgrarea_word,
    v2.MCERTWORD mcertword,
    v2.MCERTID MCERTID,
    v2.BEGINDATE BEGINDATE,
    v2.EXPIREDATE EXPIREDATE,
    v2.GRANTORGAN GRANTORGAN,
    v2.MCERTDATE MCERTDATE,
    v2.ADDRESS ADDRESS,
    v2.COMPANYSTATE_CODE COMPANYSTATE_CODE,
    v2.MCERTSTATE_CODE MCERTSTATE_CODE,
    v2.FIRSTDATE FIRSTDATE,
    v2.UPDATETIME UPDATETIME,
    v2.LOG_CHG_TIME LOG_CHG_TIME,
    v2.ID_ROW_SEQ_NO ID_ROW_SEQ_NO*/
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        Map map = new ConcurrentHashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        threadLocal.set(map);
        Executor executor = new ThreadPoolExecutor(8,16,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                threadLocal.set("1234");
                threadLocal.set(map);
                Map re = (Map)threadLocal.get();
                re.remove("1");
                System.out.println(Thread.currentThread().toString()+threadLocal.get());
            });
        }
        System.out.println(Thread.currentThread().toString()+threadLocal.get());
    }
}
