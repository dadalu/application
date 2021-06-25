package com.wxc.spring.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
@Slf4j
@Component
public class Jdbcbatch {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static String[] emissionStandards = {"国三", "国四", "国五"};
    int san = 0;
    int si = 0;
    int w = 0;

    @Transactional(rollbackOn = Exception.class)
    public void update() {
        File file = new File("E:\\obd_vehice_update.sql");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int standard = 0;
        Random random = new Random();
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        List<Info> infos = new ArrayList<>(4000000);
        List<String> list = jdbcTemplate.query("select code from obd_vehicle where emission_standard = '' and update_time< ?", ps -> {
            ps.setTimestamp(1, new Timestamp(calendar.getTime().getTime()));
        }, (rs, i) -> rs.getString("code"));
        log.info("date:{},vehicle size:{}",calendar.getTime(),list.size());
        Info info;
        String sql = "update obd_vehicle set emission_standard ='";
        StringBuffer sb;
        for (String code : list) {
            sb = new StringBuffer(sql);
            info = new Info();
            int num = random.nextInt(349);
            if (num < 159) {
                standard = 0;
                san++;
            } else if (num >= 159 && num < 331) {
                standard = 1;
                si++;
            } else if (num >= 331 && num < 349) {
                standard = 2;
                w++;
            }
            info.setCode(code);
            info.setStandard(standard);
            sb.append(emissionStandards[standard]);
            sb.append("',vehicle_model ='r' where code = '");
            sb.append(code);
            sb.append("';");
            infos.add(info);
            try {
                writer.write(sb.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("update start....");
        long stime = System.currentTimeMillis();
        /*jdbcTemplate.batchUpdate("update obd_vehicle set emission_standard =?,vehicle_model ='r' where code = ?", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Info info = infos.get(i);
                preparedStatement.setString(1, emissionStandards[info.getStandard()]);
                preparedStatement.setString(2, info.getCode());
            }

            @Override
            public int getBatchSize() {
                return infos.size();
            }
        });*/
        System.out.println("update end....,cost:"+(System.currentTimeMillis()-stime)/1000);
        System.out.println("san:" + san + ",si:" + si + ",w:" + w);
        System.out.println("update size:" + count);
    }

    class Info {
        String code;
        Integer standard;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getStandard() {
            return standard;
        }

        public void setStandard(Integer standard) {
            this.standard = standard;
        }
    }
}
