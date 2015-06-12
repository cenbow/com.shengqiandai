package cn.vfunding.vfunding.biz.all.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.account.dao.AccountFeelLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountFeelMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.system.dao.AllStatisticsMapper;
import cn.vfunding.vfunding.biz.system.dao.ArticleMapper;
import cn.vfunding.vfunding.biz.system.dao.InvestorsFeeMapper;
import cn.vfunding.vfunding.biz.system.dao.ScrollpicMapper;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class UserTest {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ScrollpicMapper scrollPicMapper;
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private AllStatisticsMapper allStatisticsMapper;
	
	@Autowired
	private IBorrowService borrowService;
	
	@Autowired
	private BorrowTenderMapper borrowTenderMapper;
	@Autowired
	private InvestorsFeeMapper investorsFeeMapper;
	@Autowired
	private AccountFeelMapper accountFeelMapper;
	@Autowired
	private AccountFeelLogMapper accountFeelLogMapper;
	
	@Autowired
	private IJmsLogService logService;
	

	
	/*@Test
	public void testTenderB(){
		Borrow borrow = borrowMapper.selectById(89);
		User tenderUser = new User();
		tenderUser.setUserId(143);
		
		AccountFeel accountFeel = accountFeelMapper.selectByUserId(tenderUser.getUserId());
		
		String s = borrowService.updateTenderFeelBorrow(borrow, tenderUser, accountFeel, new BigDecimal("600"), new BigDecimal("600"), "127111");
		
		System.out.println(s+"0000");
	}
	*/
	/*@Test
	public void testArt(){
	//	System.out.println(this.articleMapper.selectArticleByStatusCount(94,1, 0, 10).size());
	}*/
	/*@Test
	public void testaddBorrow(){
		Borrow borrow = new Borrow();
		borrow.setSiteId((short) 0);
		borrow.setContent("内容体验标体验标体验标还款还款还款还款");
		borrow.setName("体验标还款222");
		borrow.setUserId(142); // hello
		borrow.setStatus((byte) 0);
		borrow.setAddtime(Integer.parseInt(DateUtil.getTime()));
		borrow.setAddip("127.0.0.");
		borrow.setType("1");
		borrow.setValidTime(3);
		borrow.setLowestAccount(new BigDecimal("50"));
		borrow.setMostAccount(new BigDecimal("0"));
		borrow.setApr(new BigDecimal("18"));
		borrow.setTenderTimes((short) 3);
		borrow.setAccount(new BigDecimal("6000"));
		borrow.setSuccessTime(123456);
		borrow.setEndTime(456);
		borrow.setTimeLimit((short) 3);
		borrow.setStyle((byte)5);
		borrow.setBiaoType("feel");
		borrow.setOrder(0);
		borrow.setHits(0);
		borrow.setTenderTimes((short) 0);
		borrow.setRepaymentYesaccount(new BigDecimal("0"));
		borrow.setRepaymentYesinterest(new BigDecimal("0"));
		borrow.setIsLz((byte)0);
		borrow.setTenderTimes((short)0);
		borrow.setAccountYes(new BigDecimal("0"));
		borrow.setRepaymentYesaccount(new BigDecimal("0"));
		borrow.setRepaymentYesinterest(new BigDecimal("0"));
		borrow.setBorrowFee(new BigDecimal("0"));
		borrow.setTenderTimes((short)0);
		borrow.setAccountYes(new BigDecimal("0"));
		borrow.setTenderTimes((short)0);
		
		System.out.println(borrowMapper.insert(borrow));
	}*/
	/*@Test
	public void testverify(){
		
		Borrow b = borrowService.selectById(107); 
				
		Borrow borrow = new Borrow();
		borrow.setSiteId((short) 0);
		borrow.setId(107);
		borrow.setVerifyRemark("同意。。。"+borrow.getId());
		User user = new User();
		user.setUserId(1);
		
		borrow.setUserId(user.getUserId());
		borrow.setSiteId((short) 0);
		borrow.setAccount(b.getAccount());
		borrow.setContent(b.getContent());
		borrow.setName(b.getName());
		borrow.setStatus((byte) 1);//0--等待初审
		borrow.setAddtime(Integer.parseInt(DateUtil.getTime()));
		borrow.setAddip(b.getAddip());
		borrow.setType(b.getType());
		borrow.setValidTime(b.getValidTime());
		borrow.setLowestAccount(b.getLowestAccount());
		borrow.setMostAccount(b.getMostAccount());
		borrow.setApr(b.getApr());
		borrow.setSuccessTime(b.getSuccessTime()); // TODO
		borrow.setEndTime(b.getEndTime()); // TODO
		borrow.setTimeLimit(b.getTimeLimit());
		borrow.setStyle(b.getStyle());
		borrow.setBiaoType(b.getBiaoType());
		borrow.setOrder(0);
		borrow.setHits(0);

		System.out.println(borrowService.updateVerifyBorrow(borrow, user, new BigDecimal("3"), new BigDecimal("1.5"), "127.1.1.1"));
	}*/
	/*@Test
	public void testTenderB(){
		
		User tenderUser = new User();
		tenderUser.setUserId(143);
		Borrow borrow = borrowService.selectById(107);
	
		AccountFeel accountFeel = accountFeelMapper.selectByUserId(tenderUser.getUserId());
		
		System.out.println(borrowService.updateTenderFeelBorrow(borrow, tenderUser, accountFeel, new BigDecimal("6000"), new BigDecimal("6000"), "128.0.0.0"));
	}*/
	
	/*@Test
	public void testFianlVerifyB(){
		
		Borrow borrow = borrowService.selectById(107);
		List<BorrowTender> tenders = borrowTenderMapper.selectListByBorrowId(borrow.getId());
		
		System.out.println(borrowService.updateFinalVerifyBorrow(borrow, tenders, "我没意见95"+borrow.getId(), "30", "129.0.0"));
	}*/
	
	@Test
	public void testRepayB() {
		Borrow borrow = borrowService.selectById(107);
		
		//System.out.println(borrowService.updateRepayFeelBorrow(borrow, "129.0.0"));
	}
	
	/*@Test
	public void testPic(){
		System.out.println("pic"+this.scrollPicMapper.selectScrollPicByStatus(1,2).size());
	}
	
	@Test
	public void testBorrow(){
		for(Borrow b : this.borrowMapper.selectBorrowForIndex()){
			System.out.println(b.getName());
		}
		Borrow b = this.borrowMapper.selectBorrowForIndex().get(0);
		System.out.println("借款标题"+b.getName());
		System.out.println("借款金额"+b.getAccount());
		System.out.println("借款利率"+b.getApr());
		System.out.println("借款期限"+b.getTimeLimit());
		System.out.println("借款类型"+b.getType());
		System.out.println("是否担保"+b.getIsVouch());
		System.out.println("是.."+b.getExpectApr());
	}
	
	@Test
	public void testmoney(){
		System.out.println("money:"+allStatisticsMapper.selectAllStatistics().getAllTenderMoney());
	}*/
	
	/*@Test
	public void test1(){
		int i=this.userMapper.checkRegister("admin");
		System.out.println(i);
	}
	
	@Test
	public void test2(){
		int i=this.userMapper.selectUserIdByUserName("weijijin1");
		System.out.println(i);
	}*/
	
	@Test
	public void testUserMapper(){
		List<User> users=this.userMapper.selectNoTenderUser("2015-03-10");
		System.out.println(users.size());
	}
	
	@Test
	public void testUserMapperNoLogin(){
		List<User> users=this.userMapper.selectNoLoginUser(7);
		System.out.println(users.size());
	}
	
	@Test
	public void testJmsLog(){
		boolean c=this.logService.canActionByJmsId("552331d7e4b04958d8a720c7");
		System.out.println(c);
	}
	
	
	
	
}
