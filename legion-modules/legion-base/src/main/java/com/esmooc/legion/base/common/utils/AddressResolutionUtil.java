package com.esmooc.legion.base.common.utils;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 解析地址
 *
 * @param
 * @author lin
 * @return
 */
@Slf4j
public class AddressResolutionUtil {

    public static ArrayList<Address> addressResolution(String ress) {

        String regex = "^(?<province>[^省]+省|.+自治区)?(?<city>[^市]+市|.+自治州)?(?<county>[^县]+县|.+区|.+市)?(?<town>[^区]+区|.+镇)?(?<village>.*)";

        Matcher m = Pattern.compile(regex).matcher(ress);
        String province = null, city = null, county = null, town = null, village = null;
        ArrayList<Address> addresses = new ArrayList<>();
        while (m.find()) {
            Address address = new Address();
            try {
                province = m.group("province");
            } catch (Exception e) {

            }
            address.setProvince(province == null ? "" : province.trim());
            try {
                city = m.group("city");
            } catch (Exception e) {

            }
            address.setCity(city == null ? "" : city.trim());
            try {
                county = m.group("county");
            } catch (Exception e) {

            }
            address.setCounty(county == null ? "" : county.trim());
            try {
                town = m.group("town");
            } catch (Exception e) {

            }
            address.setTown(town == null ? "" : town.trim());
            try {
                village = m.group("village");
            } catch (Exception e) {

            }
            address.setVillage(village == null ? "" : village.trim());
            addresses.add(address);
        }


        return addresses;
    }


    public static Address addressResolutionOne(String ress) {

        String regex = "^(?<province>[^省]+省|.+自治区)?(?<city>[^市]+市|.+自治州)?(?<county>[^县]+县|.+区|.+市)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(ress);

        String add = "";
        while (m.find()) {
            log.info("{}", m);
            add = "";
            System.out.println(m.group());
            Address address = new Address();
            try {
                address.setProvince(m.group("province"));
                if (!StrUtil.isBlank(m.group("province"))) {
                    add = "中国-" + m.group("province");
                }
            } catch (Exception e) {

            }
            try {
                address.setCity(m.group("city"));
                if (!StrUtil.isBlank(m.group("city"))) {
                    add = add + "-" + m.group("city");
                }
            } catch (Exception e) {

            }
            try {
                address.setCounty(m.group("county"));
                if (!StrUtil.isBlank(m.group("county"))) {
                    add = add + "-" + m.group("county");
                }
            } catch (Exception e) {

            }
            try {
                address.setCounty(m.group("town"));
                if (!StrUtil.isBlank(m.group("town"))) {
                    add = add + "-" + m.group("town");
                }
            } catch (Exception e) {

            }
            try {
                address.setVillage(m.group("village"));
                if (!StrUtil.isBlank(m.group("village"))) {
                    add = add + "-" + m.group("village");
                }
            } catch (Exception e) {


            }
            System.out.println(add);
            return address;
        }


        return null;
    }


    public static ArrayList<String> toDBArr(String ress) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String regex = "^(?<province>[^省]+省|.+自治区)?(?<city>[^市]+市|.+自治州)?(?<county>[^县]+县|.+区|.+市)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
            Matcher m = Pattern.compile(regex).matcher(ress);
            while (m.find()) {
                log.info("{}", m);
                if (!StrUtil.isBlank(m.group("province"))) {
                    arrayList.add(m.group("province"));
                }


                if (!StrUtil.isBlank(m.group("city"))) {
                    arrayList.add(m.group("city"));
                }


                if (!StrUtil.isBlank(m.group("county"))) {
                    arrayList.add(m.group("county"));
                }


                if (!StrUtil.isBlank(m.group("town"))) {
                    arrayList.add(m.group("town"));
                }


                if (!StrUtil.isBlank(m.group("village"))) {
                    arrayList.add(m.group("village"));
                }

                System.out.println(arrayList);

            }


        } catch (Exception e) {
            return null;
        }

        return arrayList;
    }

    public static String toDBStr(String ress) {
        String str = "中国";
        try {
            String regex = "^(?<province>[^省]+省|.+自治区)?(?<city>[^市]+市|.+自治州)?(?<county>[^县]+县|.+区|.+市)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
            Matcher m = Pattern.compile(regex).matcher(ress);
            while (m.find()) {
                log.info("{}", m);
                if (!StrUtil.isBlank(m.group("province"))) {
                    str = str + "-" + m.group("province");
                }


                if (!StrUtil.isBlank(m.group("city"))) {
                    str = str + "-" + m.group("city");
                }


                if (!StrUtil.isBlank(m.group("county"))) {
                    str = str + "-" + m.group("county");
                }


                if (!StrUtil.isBlank(m.group("town"))) {
                    str = str + "-" + m.group("town");
                }


                if (!StrUtil.isBlank(m.group("village"))) {
                    str = str + "-" + m.group("village");
                }

                System.out.println(str);

            }


        } catch (Exception e) {
            return null;
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(addressResolutionOne("中国山东省泰安市新泰市汶南镇西鲁村委会"));
    }


}
