package lang.cad.controller;

import lang.cad.determination.Precedence;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.security.x509.AttributeNameEnumeration;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    @RequestMapping(value="/",method = RequestMethod.GET)
    public ModelAndView index(){
        Precedence precedence = new Precedence(false);
        String[][] matr=precedence.calculate();
        List<String> tableColumnList = precedence.getTableColumns();
        for(String name:tableColumnList){
            if(name.equals("<E>") || name.equals("<T>") || name.equals("<c>")){
                int k=tableColumnList.indexOf(name);
                String value ="&lt;"+name.charAt(1)+"&gt;";
                tableColumnList.set(k,value);
            }
        }
        List<List<String>> newMatr= new ArrayList<>();
        for(int i = 0;i<precedence.getMatrLength(); i++){
            List<String> row = new ArrayList<>();
            row.add(tableColumnList.get(i));
            for(String cell:matr[i]){
                row.add(cell);
            }
            newMatr.add(row);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("matr",newMatr);
        modelAndView.setViewName("index");
        modelAndView.addObject("tableColumns",tableColumnList);
        modelAndView.addObject("right",false);
        return modelAndView;
    }

    @RequestMapping(value="/right-grammar",method = RequestMethod.GET)
    public ModelAndView rightGrammar(){
        Precedence precedence = new Precedence(true);
        String[][] matr=precedence.calculate();
        List<String> tableColumnList = precedence.getTableColumns();
        for(String name:tableColumnList){
            if(name.equals("<E>") || name.equals("<T>") || name.equals("<c>") ||
                    name.equals("<E1>") || name.equals("<T1>") || name.equals("<IDN>") || name.equals("<E2>")){
                int k=tableColumnList.indexOf(name);
                String value ="&lt;"+name.substring(name.indexOf("<")+1,name.indexOf(">"))+"&gt;";
                tableColumnList.set(k,value);
            }
        }
        List<List<String>> newMatr= new ArrayList<>();
        for(int i = 0;i<precedence.getMatrLength(); i++){
            List<String> row = new ArrayList<>();
            row.add(tableColumnList.get(i));
            for(String cell:matr[i]){
                row.add(cell);
            }
            newMatr.add(row);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("matr",newMatr);
        modelAndView.setViewName("index");
        modelAndView.addObject("tableColumns",tableColumnList);
        modelAndView.addObject("right",true);
        return modelAndView;
    }



}