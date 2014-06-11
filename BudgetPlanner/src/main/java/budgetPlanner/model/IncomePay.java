package budgetPlanner.model;

import budgetPlanner.core.HibernateUtil;

public class IncomePay {
	
	private Integer userId = null;
	private double yourPay = 0;
	private double partnersPay = 0;
	private double bonuses = 0;
	private double familyPayments = 0;
	private double other = 0;
	
	public IncomePay(){		
	}
	
	public IncomePay(Integer userId) throws Exception {
		HibernateUtil hibernate = new HibernateUtil();
		IncomePay payFromDb = hibernate.getIncomePay(userId);
		if (payFromDb != null) {
			setIncomePay(payFromDb);
		} else {
			setUserId(userId);
		}
	}
	
	private void setIncomePay(IncomePay myPay) {
		setUserId(myPay.getUserId());
		setYourPay(myPay.getYourPay());
		setPartnersPay(myPay.getPartnersPay());
		setBonuses(myPay.getBonuses());
		setFamilyPayments(myPay.getFamilyPayments());
		setOther(myPay.getOther());
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getYourPay() {
		return yourPay;
	}

	public void setYourPay(double yourPay) {
		this.yourPay = yourPay;
	}

	public double getPartnersPay() {
		return partnersPay;
	}

	public void setPartnersPay(double partnersPay) {
		this.partnersPay = partnersPay;
	}

	public double getBonuses() {
		return bonuses;
	}

	public void setBonuses(double bonuses) {
		this.bonuses = bonuses;
	}

	public double getFamilyPayments() {
		return familyPayments;
	}

	public void setFamilyPayments(double familyPayments) {
		this.familyPayments = familyPayments;
	}

	public double getOther() {
		return other;
	}

	public void setOther(double other) {
		this.other = other;
	}

	public void save() throws Exception {
		HibernateUtil hibernate = new HibernateUtil();
		if (hibernate.getIncomePay(getUserId()) == null) {
			hibernate.addIncomePay(this);
		} else {
			hibernate.updateIncomePay(this);
		}
	}
	
	public double getIncomePaySum() {
		return yourPay + partnersPay + bonuses + familyPayments + other;
	}
}
