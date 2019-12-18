# 상품정보관리 프로젝트

## ajax 기능을 활용하여 상품정보 보기 실행

## 파일 업로드 기능 추가
* 파일업로드에서는 파일의 내용(파일 자체)를 직접 DB에 저장하기도 하지만 그렇게 할 경우 DBMS에 많은 부하가 생겨 성능 이슈가 발생할 수 있다
* 파일은 public(resource)으로 공개된 폴더에 저장을 하고 DB에는 파일의 경로, 이름만 저장하여 보여줄 때 그 경로를 img tag를 이용하여 보여준다

### 파일 업로드 form
* 파일 업로드를 위해서 input type="file" tag를 사용하고 파일을 선택할 수 있도록 해준다

#### 중요
* 파일 업로드 form에는 반드시 enctype이라는 속성을 지정한다
* enctype="multipart/form-data"라고 지정

### 파일 SIZE
* CommonsMultipartResolver 클래스에 파일 업로드 최대 크기를 지정해주어야 한다. 단위는 byte 단위
* 1KB = 1024Byte, 1MB = 1024KB
* 1MB = 1024 * 1024(1048576) Byte

### 파일을 저장할 폴더
* servlet-context.xml에 resources로 폴더를 설정하고 외부에 개방을 해줘야한다
* resources tag를 사용하여 mapping으로 설정한 값은 가상 디렉토리(URL 주소에 사용)
* location으로 설정한 값은 tomcat이 prj이 진행되는동안 관리하는 실제 디렉토리이다
* web에서는 server/context/mapping으로 접근을 시도하면 실제로는 location폴더의 내용에 접근할 수 있도록 설정하는 것이다

* 로컬프로젝트에 있는 파일들은 프로젝트를 서버로 배포(deploy)하면(실행시키면) 파일들이 자동으로 복사되어 문제를 일으키지 않는다
* 서버가 실행된 상태에서 파일업로드를 통해 서버에 복사한 파일은 실제 로컬에 없는 임시파일이기 때문에, 만약 서버를 clean하거나 prj를 다시 배포하게 되면 모든 내용이 삭제된다
* 이러한, 삭제되는 현상을 방지하기 위해 files 폴더를 tomcat server와 별개로 로컬에 만들어두고 이 폴더를 외부에 개방한다
* <resources mapping="/files/**" location="files:///c:/BizWork/files/" />
* 위와 같이 설정하고 URL에 server/context/files 폴더에 접근하면 실제 c에 있는 로컬파일에 접근하는 것이다
* BeanNameUrlHandlerMapping를 beans:bean으로 class를 servlet-context.xml에 등록해줘야 tomcat이 실제 로컬에 접근하게 할 수 있다

* BeanNameUrlHandlerMapping : spring dispatcher가 기본으로 사용하는데 간혹 일부 기능에서 참조를 못하는 경우가 있어 bean으로 설정한다