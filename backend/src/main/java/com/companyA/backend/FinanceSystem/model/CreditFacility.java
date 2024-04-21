package com.companyA.backend.FinanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "credit_facilities")
public class CreditFacility {

    @Id
    private String facilityId;
    private String bankName;
    private double initialLoanAmount;
    private double currentLoanAmount;
    private double monthlyLoanAmount;
    private double totalPaidAmount;
    private double interestRate;
    private int loanTermInMonths;
    private LoanStatus loanStatus;
    private Date startDate;

    public void recordPayment(double paymentAmount) {
        if (paymentAmount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive.");
        }
        this.totalPaidAmount += paymentAmount;
        this.currentLoanAmount = Math.max(0, this.currentLoanAmount - paymentAmount); // Ensure non-negative balance
        if (currentLoanAmount == 0) {
            loanStatus = LoanStatus.PAID_OFF; // Update status if fully paid
        }
        else {
            loanStatus = LoanStatus.ACTIVE;
        }
    }

    public double monthlyLoanPayment(){
        return (initialLoanAmount+initialLoanAmount*interestRate*loanTermInMonths/12)/(loanTermInMonths);
    }
    @Override
    public String toString() {
        return "CreditFacility{" +
                "facilityId='" + facilityId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", initialLoanAmount=" + initialLoanAmount +
                ", currentLoanAmount=" + currentLoanAmount +
                ", totalPaidAmount=" + totalPaidAmount +
                ", interestRate=" + interestRate +
                ", loanTermInMonths=" + loanTermInMonths +
                ", loanStatus=" + loanStatus +
                ", startDate=" + startDate +
                '}';
    }
}
enum LoanStatus{
    ACTIVE,
    PAID_OFF,
    DELINQUENT
}