package egovframework.sample.user;

public class StudyOfAnnotation {/*
 
	
	@Controller			//: 스프링의 컨트롤러 객체임을 명시하는 annotation.(클래스에서 사용)

	@Repository			//: DAO 객체(클래스에서 사용)

	@Service			//: 서비스 객체(클래스에서 사용)

	@RequestMapping		//: 특정 URI에 매칭되는 클래스 또는 메소드 임을 명시하는 annotation(클래스와 메소드에서 사용)

	@RequestParam		//: request에서 특정한 파라미터의 값을 찾아낼 때 사용하는 annotation(파라미터에서 사용)

	@RequestHeader		//: request에서 특정 HTTP 헤더 정보 추출 시에 사용하는 annotation(파라미터에서 사용)

	@PathVariable		//: 현재의 URI에서 원하는 정보를 추출 시에 사용하는 annotation(파라미터에서 사용)

	@CookieValue		//: 현재 사용자의 쿠키가 존재하면 쿠키의 이름을 이용하여 쿠키의 값을 추출하는 annotation(파라미터에서 사용)

	@ModelAttribute		//: 자동으로 해당 개체를 뷰까지 전달하도록 만드는 annotation(메소드, 파라미터에서 사용)

	@SessionAttributes	//: 세션 상에서 모델의 정보를 유지하고 싶은 경우에 사용(클래스에서 사용)

	@InitBinder			//: 파라미터를 수집해서 객체로 만들경우에 커스터마이징(메소드에서 사용)

	@ResponseBody		//: 리턴 타입이 HTTP의 응답 메시지로 전송(메소드, 리턴타입에서 사용)

	@RequestBody		//: request 문자열이 그대로 파라미터로 전달(파라미터에서 사용)
						//  요청이 온 데이터(JSON이나 XML형식)를 바로 클래스나 model로 맵핑하기위한 annotation
	
	@ResponseBody		//: view가 아닌 JSON 형식의 값을 응답할 때 사용 , 문자열을 리턴하면 그값을 http response header가 아닌 response body에 들어감
						//	만약 객체를 리턴하는 경우 JACKSON 라이브러리에 의해 문자열로 변환되어 전송됨
						//	context에 설정된 resolver를 무시한다고 보면됨  (메소드에서 사용 /리턴타입)

	@Required 			//: 메소드를 통해 값을 주도록 강요함. (property 안적어줬을 때 에러)
						//  @Required 사용하려면 <bean class=”org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor”/> 
						//  위의 형태는 너무 길어서 context 사용(동일한 효과) <context:annotation-config />

	@Autowired 			//: 내부적으로 Setter가 생성. Bean의 자동 삽입을 위해 사용. (type 으로 매핑)
						//  @Autowired를 사용하려면
						//	<bean class=”org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor”/> 
						//	위의 형태는 너무 길어서 context 사용(동일한 효과) 아래의 한줄을 적어주고 객체 생성에만 충실하면 된다!
						//	<context:annotation-config />
	
	@Qualifier			// : @Autowired를 name 으로 매핑하고 싶을 때 함께 사용.
						//	 DI를 위한 어노테이션이며 인젝션할  대상을 명시해주면 됨 

	@Resource			// : Bean의 자동 삽입을 위해 사용. (name 으로 매핑)

	@Scope				// : 스프링에서 관리하는 Bean은 Singleton 이 디폴트.
						//   Singleton : 싱글톤으로 객체를 한 개만 생성
						//   Prototype : 사용자 요청별로 별개의 객체를 생성

	@Component			// : 객체 생성. Bean 생성.
						//   @Component을 사용하려면 <context:component-scan base-package=”패키지명” />
						//   context:component-scan 만 있으면 context:annotation-config 도 필요 없다!

	@PostConstruct		// : 생성자 수행 후 수행. JDK가 지원.

	@PreDestroy			// : 응용 프로그램 종료 직전에 수행되어 마무리 작업. JDK가 지원. 내부적으로 수행되어 콘솔에 출력 안됨.

	@Value				// : 초기치로 부여할 때 사용. 사용 시 @Autowired를 생성자에 써줘야 함. 
						//	 스프링에서는 기본적으로 클래스 선언부 위에 @Component가 붙어 있으면 스프링 빈으로 생성 
							
 
	
	
	
	public void test() {
		
	}
	
	
	
	@Component
	-설정위치: 클래스 선언부 앞
	-설명: <context:component-scan> 태그를 설정파일에 추가하면 해당 어노테이션이 적용된 클래스를 빈으로 등록하게 된다. 
	범위는 기본으로 singleton이며 @Scope를 사용하여 지정할 수 있다. 사용하려면 XML 설정파일에 <context:component-scan>을 정의하고
	적용할 기본  패키지를 base-package 속성으로 등록한다.

	<!-- component-scan -->

	<context:component-scan base-package="xxx.xxx.xxx" />

	xxx.xxx.xxx 패키지 하위에 @Component로 선언된 클래스를 bean으로 자동 등록한다. 
	bean의 이름은 해당 클래스명(첫글자는 소문자)이 사용된다.

	<context:component-scan scoped-proxy="no" />

	scope-proxy는 <aop:scoped-poxy/>처럼 WebApplicationContext에서만 유효하며 
	"session", "globalSession", "request" 이외의 scope는 무시 되며 아래의 3가지 값을 설정.
	-no: proxy를 생성하지 않는다. (default)
	-interfaces: JDK Dynamic Proxy를 이용한 Proxy 생성
	-targetClass: 클래스에 대해 포록시를 생성

	스캔 대상 클래스 범위를 지정할 수 있는데 이는 <context: component-scan> 태그 안에
	<context: include-filter type="" expression=""/>, exclude-filter로 
	자동 스캔 대상에 포함시킬 클래스와 포함시키지 않을 클래스를 구체적으로 명시할 수 있다.


	@Required
	-설정위치: setter 메서드 앞
	-설명: 필수 프로퍼티임을 명시하는 것으로 필수 프로퍼티를 설정하지 않을 경우 빈 생성시 예외를 발생시킨다. 
	RequiredAnnotationBeanPostProcessor클래스는 스프링 컨테이너에 등록된
	bean 객체를 조사하여 @Required 어노테이션으로 설정되어 있는 프로퍼티의 값이 설정되어 있는지 검사한다. 
	클래스를 빈으로 등록시키는 대신에 <context: annotaion-config/> 사용 가능. 왠만한 태그
	다 동일함.


	@Autowired
	-설정위치: 생성자, 필드, 메서드 앞
	-설명: 의존관계를 자동설정할 때 사용하며 타입을 이용하여 의존하는 객체를 삽입해준다. 
	그러므로 해당 타입의 빈객체가 존재하지않거나 또는 2개 이상 존재할 경우 스프링은 예외를 발생시킨다. 
	-옵션: require = 적용한 포로퍼티 중 반드시 설정할 필요가 없는 경우 false를 주어 
	프로퍼티가 존재하지 않더라고 스프링이 예외를 발생하지 않도록 한다. 
	@Autowired(required=false)

	@Autowired
	private Test test;


	@Service
	-설명: @Service를 적용한 클래스는 비지니스 로직이 들어가는 Service로 등록이 된다.

	@Service(value = "memberService")
	public class MemberService {}

	불러올 때(@Resource사용)

	@Controller
	public class MemberController {

	 // Service 호출
	 @Resource(name = "memberService")
	 private MemberService memberService;
	}


	@Repository
	-설명: 일반적으로 DAO에 사용되며 DB Exception을 DataAccessException으로 변환
	@Repository(value = "memberDao")
	public interface MemberDao {}

	불러올 때(@Resource사용)

	@Service(value = "memberService")
	public class MemberService {

	 @Resource(name = "memberDao")
	 MemberDao memberDao;
	}


	@Qualifer
	-설정위치: @Autowired와 함께 사용.
	-설명: qualifier 어노테이션은 @Autowired의 목적에서 동일 타입의 빈객체가 존재시 특정빈을 삽입할 수 있게 설정한다. 
	@Qualifier("mainBean")의 형태로 사용하며, bean 태그에
	<qualifier value="mainBean"/>태그를 선언해주어야 한다.


	@Resource
	-설명: @Autowired와 흡사하지만 @Autowired는 타입으로, @Resource는 이름(by name) 으로 연결한다는 점이 다르다.
	-옵션: name = 자동으로 연결될 빈 객체 이름을 입력한다.
	@Resource(name="testDao")
	private TestDao testDao;


	@Scope
	-설정: prototype, singleton, request, session, globalSession
	-설명: 스프링은 기본적으로 빈의 범위를 "singleton" 으로 설정한다. 
	"singleton"이 아닌 다른 범위를 지정하고 싶다면 @Scope를 이용하여 범위를 지정한다.

	@Component
	@Scope(value="prototype")
	public class Employee{ }


	@PostConstruct
	-설정위치: 초기화 작업 수행 메서드 앞
	-설명: 의존하는 객체를 설정한 이후에 초기화 작업을 수행하기 위해 사용한다.

	@PostConstruct
	public void init() {
	    System.out.println("객체 생성 후 내가 먼저 실행된다.");
	}


	@PreDestroy
	-설정위치: 해당 작업 메서드앞
	-설명: 컨테이너에서 객체를 제거하기전에 해야할 작업을 수행하기 위해 사용.


	@SessionAttributes
	-설명: 세션상에서 model의 정보를 유지하고 싶을 경우 사용한다.


	@InitBinder
	-설명: WebDataBinder를 초기화하는 method를 지정 할 수 있는 설정을 제공한다.
	일반적으로 WebDataBinder는 annotation handler 메서드의 command와 form 객체 인자를 조작하는데 사용한다.


	@PathVariable
	-설명: URL의 일부를 파라미터 혹은 변수로 사용한다. URL경로에 변수 넣어주기

	
*/}
