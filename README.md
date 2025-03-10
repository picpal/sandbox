# 🧰 Boilerplate

<br />

## ⚙ Spec

### ◾ Base
- java : 17
- gradle : 8.5
- springboot : 3.2.2
- rest docs 0.18.2 + open api 2.3.0 + swagger UI 2.19.2

### ◾ Testing
- JUnit5
- mockito

### ◾ Java code coverage
- jacoco


### ◾ Monitoring
- actuator (spring boot 3.2.2)

### ◾ Security
- jaysypt 3.0.5
- enc
    - sha256
    - sha512

### ◾ Database
- H2
- JPA (spring boot 3.2.2)
- Mybatis 3.0.3 (RDB&테이블간 복잡한 구조인 경우 사용)

### ◾ Schedule
- quartz 2.3.2 & spring boot starter 3.2.2

<br />


## ⚙ 프로젝트 구성
- core : 공통 기능 패키지
    - aspect : AOP 
        - Controller 레이어
            - HTTP 요청 및 응답의 로깅
            - 메서드 실행 전후 로깅
            - 예외 처리 및 HTTP 상태 코드 로깅
        - Service 레이어:
            - 메서드 호출 및 실행 시간 측정
            - 서비스 메서드의 입력/출력 값 로깅
            - 트랜잭션 관련 로깅 (트랜잭션 시작, 종료 등)
        - Mapper 레이어:
            - 메서드 호출 및 실행 시간 측정
            - 서비스 메서드의 입력/출력 값 로깅
    - config : Configuration 
    - constant : 상수
    - crypt : 암,복호화
    - enums : Type, Flag , Error Code
        - ResponseCode
            - HTTP Response Code
            - Project Custom Response Code
                - success
                - validation error code
                - system error code
                - network error code
                - file processing error code
                - authentication error code
                - unknow error code
    - exception : 예외처리
    - interceptor : session 제어, guid 생성 등...
    - utils : string, date, convertor등의 util들은 직접 구성하기 보다 외부 라이브러리를 우선 사용
        - HttpReqeustUtils : get,post,delete,put 요청
        - SecureRandomUtils : 난수 생성
- [Service Name] : 프로젝트 메인 패키지

<br />


# 🐱‍🏍 Start Boilerplate :)
## 1) Set VM Option ( tomcat )
- 구성 편집 > "VM Option" jasypt 복호화를 위한 키 설정
- 아래 코드 vm 옵션으로 지정
    ```
    -Djasypt.enc.pre=0000 -Djasypt.enc.post=1111
    ```
- Basic Auth 초기 계정 bwc / bwc123 
- Jasypt 암호화는 JasyptConfigTest.java 파일 참고
  
## 2) 위 옵션 지정 후 application run 실행
- 주의사항 : springboot run과 gradle을 이용한 application bootRun은 vm옵션을 서로 공유하지 않기 때문에 각각 VM옵션을 적용해야함.

## 3) H2 DB 접속
- [url]/h2-console
- 초기 계정은 sa이나 application_local.yml을 통해 계정정보를 암호화하여 기재
- 초기 계정 : admin/admin123
