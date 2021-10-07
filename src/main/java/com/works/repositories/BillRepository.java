package com.works.repositories;

import com.works.entities.Bill;
import com.works.property.IncomeByPaymentType;
import com.works.property.MostValCustomer;
import com.works.property.SaleProperties;
import com.works.property.VisitCustomerPerWeek;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {

    @Query(value = "Select bill.bill_id ,bill.amount , bill.cus_id , bill.date, bill.note,bill.opestatus , bill.paymenttype ,\n" +
            "CASE When bill.opestatus = 0 \n" +
            "Then Concat(customer.cname, ' ' , customer.csurname) \n" +
            "When bill.opestatus = 1 Then (suppliers.sname) \n" +
            "End AS cname FROM bill \n" +
            "Inner Join customer \n" +
            "On bill.cus_id = customer.cid \n" +
            "LEFT Join suppliers \n" +
            "On bill.cus_id = suppliers.sid", nativeQuery = true)
    List<SaleProperties> saleList(Pageable pageable);

    List<Bill> findByOrderByDateAsc(Pageable pageable);

    @Query(value = "Select bill.bill_id ,bill.amount , bill.cus_id , bill.date, bill.note,bill.opestatus , bill.paymenttype ,\n" +
            "CASE When bill.opestatus = 0 \n" +
            "Then Concat(customer.cname, ' ' , customer.csurname) \n" +
            "When bill.opestatus = 1 Then (suppliers.sname) \n" +
            "End AS cname FROM bill \n" +
            "Inner Join customer \n" +
            "On bill.cus_id = customer.cid \n" +
            "LEFT Join suppliers \n" +
            "On bill.cus_id = suppliers.sid WHERE bill.paymenttype = 1", nativeQuery = true)
   List<SaleProperties> cashList(Pageable pageable);

    @Query(value = "Select bill.bill_id ,bill.amount , bill.cus_id , bill.date, bill.note,bill.opestatus , bill.paymenttype ,\n" +
            "CASE When bill.opestatus = 0 \n" +
            "Then Concat(customer.cname, ' ' , customer.csurname) \n" +
            "When bill.opestatus = 1 Then (suppliers.sname) \n" +
            "End AS cname FROM bill \n" +
            "Inner Join customer \n" +
            "On bill.cus_id = customer.cid \n" +
            "LEFT Join suppliers \n" +
            "On bill.cus_id = suppliers.sid WHERE bill.paymenttype = 2", nativeQuery = true)
    List<SaleProperties> bankCardList(Pageable pageable);

    @Query(value = "Select bill.bill_id ,bill.amount , bill.cus_id , bill.date, bill.note,bill.opestatus , bill.paymenttype ,\n" +
            "CASE When bill.opestatus = 0 \n" +
            "Then Concat(customer.cname, ' ' , customer.csurname) \n" +
            "When bill.opestatus = 1 Then (suppliers.sname) \n" +
            "End AS cname FROM bill \n" +
            "Inner Join customer \n" +
            "On bill.cus_id = customer.cid \n" +
            "LEFT Join suppliers \n" +
            "On bill.cus_id = suppliers.sid WHERE bill.paymenttype = 3", nativeQuery = true)
    List<SaleProperties> bankTransferList(Pageable pageable);

    @Query(value = "Select bill.bill_id ,bill.amount , bill.cus_id , bill.date, bill.note,bill.opestatus , bill.paymenttype , Concat(customer.cname, ' ' , customer.csurname)AS cname FROM bill Inner Join customer On bill.cus_id = customer.cid WHERE opestatus = 0", nativeQuery = true)
    List<SaleProperties> income(Pageable pageable);

    @Query(value = "Select bill.bill_id ,bill.amount , bill.cus_id , bill.date, bill.note,bill.opestatus , bill.paymenttype , Concat(customer.cname, ' ' , customer.csurname)AS cname FROM bill Inner Join customer On bill.cus_id = customer.cid WHERE opestatus = 1", nativeQuery = true)
    List<SaleProperties> expense(Pageable pageable);

    @Query(value = "Select SUM(amount) AS camount, Concat(customer.cname, ' ' , customer.csurname)AS cname FROM bill Inner Join customer On bill.cus_id = customer.cid WHERE date BETWEEN :startDate AND :endDate GROUP BY cus_id", nativeQuery = true)
    List<MostValCustomer> mostValueableCustomer(Pageable pageable, Date startDate , Date endDate);

    @Query(value = "SELECT SUM(amount) AS camount,bill.paymenttype AS paymenttype from bill GROUP BY paymentType", nativeQuery = true)
    List<IncomeByPaymentType> incomeByPaymentType(Pageable pageable);

    @Query(value = "Select COUNT(*) AS visitcount FROM bill WHERE date BETWEEN :startDate AND :endDate ", nativeQuery = true)
    Integer visitCountPerWeek(Date startDate , Date endDate);
    @Query(value = "SELECT Concat(customer.cname, ' ' , customer.csurname) AS cname, COUNT(*) AS visitcount FROM bill INNER JOIN customer ON bill.cus_id = customer.cid WHERE date BETWEEN :startDate AND :endDate GROUP BY bill.cus_id ORDER BY visitCount DESC LIMIT 1", nativeQuery = true)
    List<VisitCustomerPerWeek> visitCustomerPerWeek(Date startDate , Date endDate);







}
