package com.mhist.studyJava.config;

import lombok.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonBuilderUtils;

@Configuration
public class CommonConfig{

    /**
     * 如果Country是第三方对象的情况下
     */
//    @ConditionalOnProperty(prefix:"country",name={"name","system"})
//    @Bean
//    public Country country(@Value("${country.name}" String name, @Value("${country.system}" String system)){

    /*
    * 上述这种情况、如果在application.yml中没有配置
    * country:
           name:jiangyd
           system: socialism
     的情况下、会报错、我们的需要时在没有配置时就不让它自动扫描了、这个时候 可以使用  @Conditional 注解(SpringBoot提供的设置生效条件的注解)
     *
     * 下面是三个主要的注解：https://www.bilibili.com/video/BV14z4y1N7pg?p=10&spm_id_from=pageDriver&vd_source=d5111269b016ca70626af94fe0b41736
@ConditionalOnProperty               //   配置文件中存在对应的属性、才声明该bean
@ConditionalOnMissingClass           //   当不存在当前类型的bean时、才声明该bean
@ConditionalOnClass                  //   当前环境存在指定的这个类时、才声明该bean
    * */
//        Conutry conutry = new Conutry();
//        conutry.setName(name);
//        country.setSystem(system);
//        return conutry;
//    }

    // 需求：如果ioc容器不存在Country,则注入Province、否则不注入
    //    @ConditionalOnMissingClass(Country.class)
    //    @Bean("aa")   如果未指定name属性、则会使用方法名
//    public Province province(Country country){
//        System.out.println("province:" + country);
//        return new Province();
//    }

    /**
     * 如果方法的内容需要使用ioc容器中已经存在的bean对象、那么只需要在方法上声明即可、spring全自动注入
     */

}
