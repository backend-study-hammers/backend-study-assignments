package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.Fixdiscountpolicy;
import hello.core.discount.RatediscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(Config.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 이름으로 검색하면 된다.")
    void findBeanByParentTypeNotBeDuplicate(){
        DiscountPolicy discountPolicy=ac.getBean("ratediscount",DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(RatediscountPolicy.class);
    }
    @Test
    @DisplayName("특정 하위 타입으로 검색")
    void findBeanBysubType(){
        RatediscountPolicy ratediscountPolicy= ac.getBean(RatediscountPolicy.class);
        assertThat(ratediscountPolicy).isInstanceOf(RatediscountPolicy.class);
    }
    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for(String key: beansOfType.keySet()){
            System.out.println("key= " +key+" value= "+beansOfType.get(key));
        }
    }
    @Test
    @DisplayName("부모 타입으로 모두 조회하기-Object")
    void findAllBeanByParentTypeObject(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key: beansOfType.keySet()){
            System.out.println("key= " +key+" value= "+beansOfType.get(key));
        }
    }

    @Configuration
    static class Config{
        @Bean
        DiscountPolicy ratediscount(){
            return new RatediscountPolicy();
        }
        @Bean
        DiscountPolicy fixeddiscount(){
            return new Fixdiscountpolicy();
        }
    }
}