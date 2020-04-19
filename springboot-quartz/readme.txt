一：Scheduled定时任务器
	Scheduled定时任务：是spring3.0以后自带的一个定时任务器。
	1.在pom文件中添加Scheduled的坐标
		<!-- 添加scheduled坐标 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
		</dependency>
	2.编写定时任务类
		@Component
		public class ScheduledDemo {
			/*
			 * 定时任务方法
			 * @Scheduled:设置定时任务
			 * cron属性：cron表达式。定时任务触发时间的一个字符串表达形式
			 */
			@Scheduled(cron="0/2 * * * * ?")
			public void scheduledMehode() {
				System.out.println("定时器被触发"+new Date());
			}
		}
	3.在启动类中开启定时任务的使用
		@EnableScheduling
	4.cron表达式讲解
		cron表达式是一个字符串，分为6个或7个域，每一个域代表一个含义
		cron有如下两种格式：
			1）Seconds Minutes Hours Day Month Week Year
			2）Seconds Minutes Hours Day Month Week
		一.结构
			corn从做到右(用空格隔开):秒 分 小时 月份中的日期 月份 星期中的日期 年份
		二.各字符的含义
			位置	时间域名	允许值		允许的特殊字符
			1		秒			0-59		, - * /
			2		分钟		0-59		, - * /
			3		小时		0-23		, - * /
			4		日			1-31		, - * / L W C
			5		月			1-12		, - * /
			6		星期		1-7			, - * / ? L C #			//1表示星期天,7表示星期六
			7		年(可选)	1970-2099	, - * /
		
			*:可用在所有字段中,表示对应时间域的每一个时刻
			?:只在日期和星期字段中使用,通常指定为"无意义的值",相当于占位符
			-:表达一个范围,如在小时中使用"10-12",则表示从10到12点,即10,11,12
			,:表达一个列表值,如在星期中使用"mon,wed,fri",则表示星期一,星期三和星期五
			/:x/y表达一个等步长序列,x位起始值,y为增量步长值
			L:在日期和星期字段中使用,代表"last"的意思;L在日期字段中,表示这个月份的最后一天,若
				L在星期中,则表示星期六,等同于7. 6L表示该月的左后星期五
			W:只出现在日期字段里,表示离该日期最近的工作日
			LW:当月的最后一个工作日
			#:只能在星期字段中使用,表示当月某个工作日.如6#3表示当月的第三个星期五
			C:只在日期和星期字段中使用,计划所关联的日期
		
			cron表达式对特殊字符的大小写不敏感,对代表星期的缩写英文大小写也不敏感
		
二:springboot整合Quartz定时任务框架
	1.Quartz的介绍以及Quartz的使用思路
		1.1Quartz的介绍
			quartz（开源项目）
			Quartz是OpenSymphony开源组织在Job scheduling领域又一个开源项目，
			它可以与J2EE与J2SE应用程序相结合也可以单独使用。Quartz可以用来创建简单或为
			运行十个，百个，甚至是好几万个Jobs这样复杂的程序。Jobs可以做成标准的Java组件或
			EJBs。Quartz的最新版本为Quartz 2.3.0
		1.2Quartz的使用思路
			1)job -任务 -你要做什么事? 
			2)trigger -触发器 -你什么时候去做?
			3)scheduler -任务调度 -你什么时候需要去做什么事?
	2.Quartz的基本使用方式(java项目中)
		2.1.添加quartz坐标
			<!-- quartz坐标 -->
			<dependency>
				<groupId>org.quartz-scheduler</groupId>
				<artifactId>quartz</artifactId>
				<version>2.2.1</version>
			</dependency>
		2.2.创建Job类
			public class QuartzDemo implements Job {
				/*
				 * 任务被触发时所执行的方法
				 */
				@Override
				public void execute(JobExecutionContext context) throws JobExecutionException {
					System.out.println("Execute..."+new Date());
				}
			}
		2.3.编写测试代码
			public class QuartzMain {
				public static void main(String[] args) {
					//1.创建Job对象，要做什么事情？
					JobDetail job = JobBuilder.newJob(QuartzDemo.class).build();
					
					/* schedBuilder：
					 * 1.简单的trigger触发时间，通过Quartz提供一个方法完成简单的重复调用
					 * 2.cron Trigger
					 */
					//2.创建Trigger对象，在什么时间做？
					//2.1简单的trigger触发时间
					/*Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();*/
					//2.2cron表达式
					Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();
					
					//3.创建Scheduler对象，在什么时间做什么事？
					try {
						Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
						scheduler.scheduleJob(job, trigger);
						//启动
						scheduler.start();
					} catch (SchedulerException e) {
						e.printStackTrace();
					}	
				}		
			}
	3.springboot整合quartz定时框架
		3.1修改pom文件添加坐标
			<!-- quartz坐标 -->
			<dependency>
				<groupId>org.quartz-scheduler</groupId>
				<artifactId>quartz</artifactId>
				<exclusions>
					<exclusion>
						<groupId>org.slf4j</groupId>
						<artifactId>slf4j-api</artifactId>
					</exclusion>
				</exclusions>
			</dependency>
			<!-- scheduled坐标 -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-context-support</artifactId>
			</dependency>
			<!-- spring-tx事务坐标 -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-tx</artifactId>
			</dependency>
		3.2编写quartz的启动类
			@Configuration
			public class QuartzConfig {
				/*
				 * 1.创建Job对象
				 */
				@Bean
				public JobDetailFactoryBean getJobDetailFactoryBean() {
					JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
					//关联自己的job类
					jobDetail.setJobClass(QuartzDemo.class);
					return jobDetail;
				}
				
				/*
				 * 2.创建Trigger对象
				 */
				@Bean
				public SimpleTriggerFactoryBean getSimpleTriggerFactoryBean(JobDetailFactoryBean jobDetail) {
					SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();
					//关联jobDetail对象
					simpleTrigger.setJobDetail(jobDetail.getObject());
					//该参数表示一个执行的毫秒数
					simpleTrigger.setRepeatInterval(2000);
					//重复次数
					simpleTrigger.setRepeatCount(5);
					return simpleTrigger; 
				}
				
				/*
				 * 3.创建Scheduler对象
				 */
				@Bean
				public SchedulerFactoryBean getSchedulerFactoryBean(SimpleTriggerFactoryBean simpleTrigger) {
					SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
					//关联Trigger
					scheduler.setTriggers(simpleTrigger.getObject());
					return scheduler;
				}
			}
		3.3修改启动类
			@EnableScheduling
	4.Job类中注入对象
		4.1注入时会产生异常
		4.2编写一个MyAdaptableJobFactory解决该问题
			@Component("myAdaptableJobFactory")
			public class MyAdaptableJobFactory extends AdaptableJobFactory{

				//AutowireCapableBeanFactory 将一个对象添加到IOC容器中,并且完成该对象的注入
				@Autowired
				private AutowireCapableBeanFactory autowireCapableBeanFactory;
				
				/*
				 * 该方法需要将实例化的任务对象手动的添加到springIOC容器中并且完成对象的注入
				 */
				@Override
				protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
					Object object = super.createJobInstance(bundle);
					//将对象添加到springIOC容器中,并完成注入
					autowireCapableBeanFactory.autowireBean(object);
					return object;
				}
			}
		4.3在quartz配置类中
			创建Scheduler对象中加入:
			//关联任务工厂
			scheduler.setJobFactory(myAdaptableJobFactory);
			












	
		
		
		
		
	