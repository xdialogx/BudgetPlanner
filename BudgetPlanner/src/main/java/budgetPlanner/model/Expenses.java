package budgetPlanner.model;

import budgetPlanner.core.HibernateUtil;

public class Expenses {
	
	private Integer userId = null;
	private double financialCommitments = 0;
	private double homeExpenses = 0;
	private double utilitiesExpenses = 0;
	private double educationExpenses = 0;
	private double healthExpenses = 0;
	private double shoppingExpenses = 0;
	private double transportExpenses = 0;
	private double entertainmentExpenses = 0;
	private double eatingOutExpenses = 0;
	private double otherExpenses = 0;
	
	public Expenses() {
		
	}
	
	public Expenses(Integer userId) throws Exception {
		HibernateUtil hibernate = new HibernateUtil();
		Expenses expensesFromDb = hibernate.getExpenses(userId);
		if (expensesFromDb != null) {
			setExpenses(expensesFromDb);
		} else {
			setUserId(userId);
		}
	}
	
	private void setExpenses(Expenses myExpenses) {
		setUserId(myExpenses.getUserId());
		setFinancialCommitments(myExpenses.getFinancialCommitments());
		setHomeExpenses(myExpenses.getHomeExpenses());
		setUtilitiesExpenses(myExpenses.getUtilitiesExpenses());
		setEducationExpenses(myExpenses.getEducationExpenses());
		setHealthExpenses(myExpenses.getHealthExpenses());
		setShoppingExpenses(myExpenses.getShoppingExpenses());
		setTransportExpenses(myExpenses.getTransportExpenses());
		setEntertainmentExpenses(myExpenses.getEntertainmentExpenses());
		setEatingOutExpenses(myExpenses.getEatingOutExpenses());
		setOtherExpenses(myExpenses.getOtherExpenses());
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public double getFinancialCommitments() {
		return financialCommitments;
	}

	public void setFinancialCommitments(double financialCommitments) {
		this.financialCommitments = financialCommitments;
	}

	public double getHomeExpenses() {
		return homeExpenses;
	}

	public void setHomeExpenses(double homeExpenses) {
		this.homeExpenses = homeExpenses;
	}

	public double getUtilitiesExpenses() {
		return utilitiesExpenses;
	}

	public void setUtilitiesExpenses(double utilitiesExpenses) {
		this.utilitiesExpenses = utilitiesExpenses;
	}

	public double getEducationExpenses() {
		return educationExpenses;
	}

	public void setEducationExpenses(double educationExpenses) {
		this.educationExpenses = educationExpenses;
	}

	public double getHealthExpenses() {
		return healthExpenses;
	}

	public void setHealthExpenses(double healthExpenses) {
		this.healthExpenses = healthExpenses;
	}

	public double getShoppingExpenses() {
		return shoppingExpenses;
	}

	public void setShoppingExpenses(double shoppingExpenses) {
		this.shoppingExpenses = shoppingExpenses;
	}

	public double getTransportExpenses() {
		return transportExpenses;
	}

	public void setTransportExpenses(double transportExpenses) {
		this.transportExpenses = transportExpenses;
	}

	public double getEntertainmentExpenses() {
		return entertainmentExpenses;
	}

	public void setEntertainmentExpenses(double entertainmentExpenses) {
		this.entertainmentExpenses = entertainmentExpenses;
	}

	public double getEatingOutExpenses() {
		return eatingOutExpenses;
	}

	public void setEatingOutExpenses(double eatingOutExpenses) {
		this.eatingOutExpenses = eatingOutExpenses;
	}

	public double getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(double otherExpenses) {
		this.otherExpenses = otherExpenses;
	}
	
	public void save() throws Exception {
		HibernateUtil hibernate = new HibernateUtil();
		if (hibernate.getExpenses(getUserId()) == null) {
			hibernate.addExpenses(this);
		} else {
			hibernate.updateExpenses(this);
		}
	}
	
	public double getExpensesSum() {
		return (financialCommitments + homeExpenses + utilitiesExpenses + 
				educationExpenses + healthExpenses + shoppingExpenses + 
				transportExpenses + entertainmentExpenses + eatingOutExpenses +
				otherExpenses)*(-1);
	}

}
