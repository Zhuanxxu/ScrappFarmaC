package cleanTest;


import pages.FarmaciaPage;

import pages.FarmaciaPageWithOtherMethod;
import utils.GetProperties;

import java.util.ArrayList;
import java.util.List;


public class TestBaseCucumber {

    protected String v_user = GetProperties.getInstance().getV_user();
    protected String v_pwd = GetProperties.getInstance().getV_pwd();
    protected String v_dni = GetProperties.getInstance().getV_dni();
    protected String s_user = GetProperties.getInstance().getS_user();
    protected String s_pwd = GetProperties.getInstance().getS_pwd();
    protected String s_dni = GetProperties.getInstance().getS_dni();;
    public FarmaciaPage farmaciaPage = new FarmaciaPage();
    public FarmaciaPageWithOtherMethod farmaciaPage2ndMethod = new FarmaciaPageWithOtherMethod();
    List<List<String>> articulos = new ArrayList<List<String>>();



}
