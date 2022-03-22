package vn.vimass.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import CMC.CMCWebservice;
import FPT.FPTWebservice;


@ApplicationPath("/services")
public class Startup extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<>();
        
        result.add(CMCWebservice.class);
        
        result.add(FPTWebservice.class);
        
        result.add(MOXyJsonContentResolver.class);
        return result;
    }
}