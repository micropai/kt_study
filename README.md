## 사내 교육용 Sample source

## 1주차
1. 기본 spring 개념
1. controller and open-api

## 2주차
1. configuration
1. test code
1. @componet의 갈래들에 대한 설명

## 3주차 
1. lombok
1. spring mvc 흐름에 대한 설명
1. validation

## 4주차
1. check exception 설명 및 runtimeException 설명
2. enum 관련 설명
3. 최신 자바의 추가 기능 설명
4. Java 람다와 stream 사용법

## 5주차
1. 스프링 스케쥴러
1. 이벤트를 사용하는 이유 및 spring 의 기본 이벤트 개발 방식 설명
1. 어노테이션 기반의 event 및 eventlistener
1. 어노테이션 기반의 트랜잭션이벤트(Jpa 시 재설명)

## 6주차
1. jpa 기본
    + 기본 개념
    + 설정
    + h2
    + entity
    + 연관 관계
    + 영속성
    + jpql
1. jpa test
    + slice test jpa jdbc

## 7주차
1. c-s->r 연계
1. 통합테스트
1. 모킹테스트


## 8주차
1. apt 간단 설명
1. querydsl

## 9주차
1. mini 프로젝트
    + open api 제공
    + feign 으로 통신

---
## 기타 유용한 기술 설명

<details>
<summary>Intellij Http Client</summary>
<div>       

Intellij 의 http client 설정 관련 설명입니다.

|파일|설명|
|---|---|
|http-client.env.json|공통 변수 설정|
|http-client.private.env.json|git 에 저장하지 않을 인증관련한 정보를 설정|

+ http client 에서는 간단한 annotatin 을 지원합니다.
로그 파일 생성하지 않음
```http request
###
// @no-log
GET  https://{{url}}/api/Member/1

```

Jwt 토큰을 쿠키로 심는 예시
```http request
// @no-log
POST https://{{url}}/api/login

{ "user" : "{{username}}","pwd":"{{password}}" } 

> {%
client.global.set("Cookie", "Auth-cookie==" + response.body.imdata[0].token);
client.log(client.global.get("imdata[0].token"));
%}

###

```

</div>
</details>

<details>
<summary>Gradle Catalog</summary>
<div>
gradle 에서는 maven bom 을 대체하기 위하여 catalog 를 지원합니다.

+ catalog 를 생성하기 위한 gradle 예시
```groovy
plugins {
    id 'version-catalog'
    id 'maven-publish'
}

group 'com.kt.itzy'
version '0.4.1'

catalog {
    versionCatalog {
        from files("libs.versions.toml")
    }
}

publishing {
    // ~~~
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```

+ gradle 에서 버전을 처리해도 되고 별도의 toml 로 추가 가능함
```toml
[versions]
spring-boot = "2.6.3"
spring-dependency-management = "1.0.11.RELEASE"
spring-cloud-dependencies = "2021.0.0"
commons-lang3 = "3.12.0"
commons-collections4 = "4.4"
modelmapper = "3.0.0"
common-version = { prefer = "[0.4.0, 0.5.0["}
jsr305 = "3.0.2"
jasypt-spring-boot-starter = "3.0.4"

[libraries]
spring-boot-dependencies = {group = "org.springframework.boot", name = "spring-boot-dependencies", version.ref = "spring-boot" }
spring-cloud-dependencies = {group = "org.springframework.cloud", name = "spring-cloud-dependencies", version.ref = "spring-cloud-dependencies" }
commons-lang3 = {group = "org.apache.commons", name = "commons-lang3", version.ref = "commons-lang3" }
commons-collections4 = {group = "org.apache.commons", name = "commons-collections4", version.ref = "commons-collections4" }
modelmapper = {group = "org.modelmapper", name = "modelmapper", version.ref = "modelmapper" }
jsr305= {group = "com.google.code.findbugs", name = "jsr305", version.ref = "jsr305" }
jasypt-spring-boot-starter= {group = "com.github.ulisesbocchio", name = "jasypt-spring-boot-starter", version.ref = "jasypt-spring-boot-starter" }

common-manager = {group = "com.kt.itzy", name = "common-manager", version.ref = "common-version" }
common-bom = {group = "com.kt.itzy", name = "itzy-bom", version.ref = "common-version" }

[plugins]
dependency-management = {id = "io.spring.dependency-management", version.ref = "spring-dependency-management" }
spring-boot = {id = "org.springframework.boot", version.ref = "spring-boot" }

[bundles]
common-lib = ["commons-lang3", "commons-collections4", "modelmapper", "jsr305", "jasypt-spring-boot-starter"]
```

+ catalog 를 사용하는 쪽에서 setting.gradle 에 설정해야 할 정보
```groovy
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        maven {
           ~~~ customRepository setting
        }
    }
    versionCatalogs {
        libs {
            from("catalog 지정")
        }
    }
}

```
</div>
</details>
