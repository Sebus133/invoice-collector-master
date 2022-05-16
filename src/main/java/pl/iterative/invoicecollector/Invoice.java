package pl.iterative.invoicecollector;

import lombok.Data;

@Data
public class Invoice {
    String acquisitionTimestamp ;
    String currency;
    String invoiceReferenceNumber;
    String invoicingDate;
    String ksefReferenceNumber;
    String net;
    String vat;

}
