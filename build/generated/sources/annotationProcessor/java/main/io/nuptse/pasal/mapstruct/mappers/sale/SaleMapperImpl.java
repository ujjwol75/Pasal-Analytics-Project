package io.nuptse.pasal.mapstruct.mappers.sale;

import io.nuptse.pasal.mapstruct.dto.client.ClientSlimDto;
import io.nuptse.pasal.mapstruct.dto.customer.CustomerSlimDto;
import io.nuptse.pasal.mapstruct.dto.product.ProductSlimDto;
import io.nuptse.pasal.mapstruct.dto.sale.SaleGetDto;
import io.nuptse.pasal.mapstruct.dto.sale.SaleListDto;
import io.nuptse.pasal.mapstruct.dto.sale.SalePostDto;
import io.nuptse.pasal.mapstruct.dto.sale.SaleSlimDto;
import io.nuptse.pasal.mapstruct.dto.sale.lineitem.LineItemGetDto;
import io.nuptse.pasal.mapstruct.dto.sale.lineitem.LineItemPostDto;
import io.nuptse.pasal.mapstruct.dto.sale.payment.PaymentGetDto;
import io.nuptse.pasal.mapstruct.dto.sale.payment.PaymentPostDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.Customer;
import io.nuptse.pasal.models.product.Product;
import io.nuptse.pasal.models.sale.LineItem;
import io.nuptse.pasal.models.sale.Payment;
import io.nuptse.pasal.models.sale.Sale;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public SaleGetDto saleToSaleGetDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleGetDto.SaleGetDtoBuilder saleGetDto = SaleGetDto.builder();

        saleGetDto.items( lineItemListToLineItemGetDtoList( sale.getLineItems() ) );
        saleGetDto.payments( paymentListToPaymentGetDtoList( sale.getPayments() ) );
        saleGetDto.id( sale.getId() );
        saleGetDto.total( sale.getTotal() );
        saleGetDto.partialAmount( sale.getPartialAmount() );
        saleGetDto.discount( sale.getDiscount() );
        saleGetDto.subTotal( sale.getSubTotal() );
        saleGetDto.grandTotal( sale.getGrandTotal() );
        saleGetDto.isVoid( sale.getIsVoid() );
        saleGetDto.isCredit( sale.getIsCredit() );
        saleGetDto.billAt( sale.getBillAt() );
        saleGetDto.client( clientToClientSlimDto( sale.getClient() ) );
        saleGetDto.customer( customerToCustomerSlimDto( sale.getCustomer() ) );
        saleGetDto.remarks( sale.getRemarks() );
        saleGetDto.totalPayment( sale.getTotalPayment() );
        saleGetDto.dues( sale.getDues() );
        saleGetDto.grossAmount( sale.getGrossAmount() );

        return saleGetDto.build();
    }

    @Override
    public Sale toEntity(SaleGetDto saleGetDto) {
        if ( saleGetDto == null ) {
            return null;
        }

        Sale.SaleBuilder<?, ?> sale = Sale.builder();

        sale.payments( paymentGetDtoListToPaymentList( saleGetDto.getPayments() ) );
        sale.lineItems( lineItemGetDtoListToLineItemList( saleGetDto.getItems() ) );
        sale.id( saleGetDto.getId() );
        sale.total( saleGetDto.getTotal() );
        sale.partialAmount( saleGetDto.getPartialAmount() );
        sale.discount( saleGetDto.getDiscount() );
        sale.subTotal( saleGetDto.getSubTotal() );
        sale.grandTotal( saleGetDto.getGrandTotal() );
        sale.remarks( saleGetDto.getRemarks() );
        sale.isVoid( saleGetDto.getIsVoid() );
        sale.isCredit( saleGetDto.getIsCredit() );
        sale.billAt( saleGetDto.getBillAt() );
        sale.totalPayment( saleGetDto.getTotalPayment() );
        sale.dues( saleGetDto.getDues() );
        sale.grossAmount( saleGetDto.getGrossAmount() );
        sale.client( clientSlimDtoToClient( saleGetDto.getClient() ) );
        sale.customer( customerSlimDtoToCustomer( saleGetDto.getCustomer() ) );

        return sale.build();
    }

    @Override
    public SalePostDto saleToSalePostDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SalePostDto.SalePostDtoBuilder salePostDto = SalePostDto.builder();

        salePostDto.client( saleClientId( sale ) );
        salePostDto.customer( saleCustomerId( sale ) );
        salePostDto.id( sale.getId() );
        salePostDto.total( sale.getTotal() );
        salePostDto.partialAmount( sale.getPartialAmount() );
        salePostDto.discount( sale.getDiscount() );
        salePostDto.subTotal( sale.getSubTotal() );
        salePostDto.grandTotal( sale.getGrandTotal() );
        salePostDto.isVoid( sale.getIsVoid() );
        salePostDto.isCredit( sale.getIsCredit() );
        salePostDto.billAt( sale.getBillAt() );
        salePostDto.remarks( sale.getRemarks() );
        salePostDto.payments( paymentListToPaymentPostDtoList( sale.getPayments() ) );
        salePostDto.totalPayment( sale.getTotalPayment() );
        salePostDto.dues( sale.getDues() );
        salePostDto.grossAmount( sale.getGrossAmount() );

        return salePostDto.build();
    }

    @Override
    public Sale salePostDtoToSale(SalePostDto salePostDto) {
        if ( salePostDto == null ) {
            return null;
        }

        Sale.SaleBuilder<?, ?> sale = Sale.builder();

        sale.client( salePostDtoToClient( salePostDto ) );
        sale.customer( salePostDtoToCustomer( salePostDto ) );
        sale.id( salePostDto.getId() );
        sale.total( salePostDto.getTotal() );
        sale.partialAmount( salePostDto.getPartialAmount() );
        sale.discount( salePostDto.getDiscount() );
        sale.subTotal( salePostDto.getSubTotal() );
        sale.grandTotal( salePostDto.getGrandTotal() );
        sale.remarks( salePostDto.getRemarks() );
        sale.isVoid( salePostDto.getIsVoid() );
        sale.isCredit( salePostDto.getIsCredit() );
        sale.billAt( salePostDto.getBillAt() );
        sale.totalPayment( salePostDto.getTotalPayment() );
        sale.dues( salePostDto.getDues() );
        sale.grossAmount( salePostDto.getGrossAmount() );
        sale.payments( paymentPostDtoListToPaymentList( salePostDto.getPayments() ) );

        return sale.build();
    }

    @Override
    public List<SaleListDto> salesToSaleListDtos(List<Sale> sales) {
        if ( sales == null ) {
            return null;
        }

        List<SaleListDto> list = new ArrayList<SaleListDto>( sales.size() );
        for ( Sale sale : sales ) {
            list.add( saleToSaleListDto( sale ) );
        }

        return list;
    }

    @Override
    public LineItemPostDto lineItemToLineItemPostDto(LineItem lineItem) {
        if ( lineItem == null ) {
            return null;
        }

        LineItemPostDto.LineItemPostDtoBuilder lineItemPostDto = LineItemPostDto.builder();

        lineItemPostDto.product( lineItemProductId( lineItem ) );
        lineItemPostDto.quantity( lineItem.getQuantity() );
        lineItemPostDto.unitPrice( lineItem.getUnitPrice() );
        lineItemPostDto.subTotal( lineItem.getSubTotal() );
        lineItemPostDto.particulars( lineItem.getParticulars() );
        lineItemPostDto.sale( saleToSalePostDto( lineItem.getSale() ) );

        return lineItemPostDto.build();
    }

    @Override
    public PaymentPostDto paymentToPaymentPostDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentPostDto paymentPostDto = new PaymentPostDto();

        paymentPostDto.setId( payment.getId() );
        paymentPostDto.setAmount( payment.getAmount() );
        paymentPostDto.setBillDate( payment.getBillDate() );
        paymentPostDto.setSale( saleToSalePostDto( payment.getSale() ) );

        return paymentPostDto;
    }

    @Override
    public List<SaleGetDto> salesToSaleAllDtos(List<SaleGetDto> sales) {
        if ( sales == null ) {
            return null;
        }

        List<SaleGetDto> list = new ArrayList<SaleGetDto>( sales.size() );
        for ( SaleGetDto saleGetDto : sales ) {
            list.add( saleGetDto );
        }

        return list;
    }

    @Override
    public SaleListDto saleToSaleListDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleListDto.SaleListDtoBuilder saleListDto = SaleListDto.builder();

        if ( sale.getId() != null ) {
            saleListDto.id( sale.getId().intValue() );
        }
        saleListDto.customer( sale.getCustomer() );

        return saleListDto.build();
    }

    @Override
    public Payment paymentDtoToEntity(PaymentPostDto dto) {
        if ( dto == null ) {
            return null;
        }

        Payment.PaymentBuilder<?, ?> payment = Payment.builder();

        payment.id( dto.getId() );
        payment.amount( dto.getAmount() );
        payment.billDate( dto.getBillDate() );
        payment.sale( salePostDtoToSale( dto.getSale() ) );

        return payment.build();
    }

    protected ProductSlimDto productToProductSlimDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductSlimDto productSlimDto = new ProductSlimDto();

        if ( product.getId() != null ) {
            productSlimDto.setId( product.getId() );
        }
        productSlimDto.setName( product.getName() );

        return productSlimDto;
    }

    protected SaleSlimDto saleToSaleSlimDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleSlimDto saleSlimDto = new SaleSlimDto();

        if ( sale.getId() != null ) {
            saleSlimDto.setId( sale.getId() );
        }

        return saleSlimDto;
    }

    protected LineItemGetDto lineItemToLineItemGetDto(LineItem lineItem) {
        if ( lineItem == null ) {
            return null;
        }

        LineItemGetDto.LineItemGetDtoBuilder lineItemGetDto = LineItemGetDto.builder();

        lineItemGetDto.product( productToProductSlimDto( lineItem.getProduct() ) );
        lineItemGetDto.quantity( lineItem.getQuantity() );
        lineItemGetDto.unitPrice( lineItem.getUnitPrice() );
        lineItemGetDto.subTotal( lineItem.getSubTotal() );
        lineItemGetDto.particulars( lineItem.getParticulars() );
        lineItemGetDto.sale( saleToSaleSlimDto( lineItem.getSale() ) );

        return lineItemGetDto.build();
    }

    protected List<LineItemGetDto> lineItemListToLineItemGetDtoList(List<LineItem> list) {
        if ( list == null ) {
            return null;
        }

        List<LineItemGetDto> list1 = new ArrayList<LineItemGetDto>( list.size() );
        for ( LineItem lineItem : list ) {
            list1.add( lineItemToLineItemGetDto( lineItem ) );
        }

        return list1;
    }

    protected PaymentGetDto paymentToPaymentGetDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentGetDto paymentGetDto = new PaymentGetDto();

        paymentGetDto.setId( payment.getId() );
        paymentGetDto.setAmount( payment.getAmount() );
        paymentGetDto.setBillDate( payment.getBillDate() );
        paymentGetDto.setSale( saleToSaleSlimDto( payment.getSale() ) );

        return paymentGetDto;
    }

    protected List<PaymentGetDto> paymentListToPaymentGetDtoList(List<Payment> list) {
        if ( list == null ) {
            return null;
        }

        List<PaymentGetDto> list1 = new ArrayList<PaymentGetDto>( list.size() );
        for ( Payment payment : list ) {
            list1.add( paymentToPaymentGetDto( payment ) );
        }

        return list1;
    }

    protected ClientSlimDto clientToClientSlimDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientSlimDto.ClientSlimDtoBuilder clientSlimDto = ClientSlimDto.builder();

        if ( client.getId() != null ) {
            clientSlimDto.id( client.getId().intValue() );
        }
        clientSlimDto.name( client.getName() );

        return clientSlimDto.build();
    }

    protected CustomerSlimDto customerToCustomerSlimDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerSlimDto customerSlimDto = new CustomerSlimDto();

        if ( customer.getId() != null ) {
            customerSlimDto.setId( customer.getId() );
        }
        customerSlimDto.setName( customer.getName() );

        return customerSlimDto;
    }

    protected Sale saleSlimDtoToSale(SaleSlimDto saleSlimDto) {
        if ( saleSlimDto == null ) {
            return null;
        }

        Sale.SaleBuilder<?, ?> sale = Sale.builder();

        sale.id( saleSlimDto.getId() );

        return sale.build();
    }

    protected Payment paymentGetDtoToPayment(PaymentGetDto paymentGetDto) {
        if ( paymentGetDto == null ) {
            return null;
        }

        Payment.PaymentBuilder<?, ?> payment = Payment.builder();

        payment.id( paymentGetDto.getId() );
        payment.amount( paymentGetDto.getAmount() );
        payment.billDate( paymentGetDto.getBillDate() );
        payment.sale( saleSlimDtoToSale( paymentGetDto.getSale() ) );

        return payment.build();
    }

    protected List<Payment> paymentGetDtoListToPaymentList(List<PaymentGetDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Payment> list1 = new ArrayList<Payment>( list.size() );
        for ( PaymentGetDto paymentGetDto : list ) {
            list1.add( paymentGetDtoToPayment( paymentGetDto ) );
        }

        return list1;
    }

    protected Product productSlimDtoToProduct(ProductSlimDto productSlimDto) {
        if ( productSlimDto == null ) {
            return null;
        }

        Product.ProductBuilder<?, ?> product = Product.builder();

        product.id( productSlimDto.getId() );
        product.name( productSlimDto.getName() );

        return product.build();
    }

    protected LineItem lineItemGetDtoToLineItem(LineItemGetDto lineItemGetDto) {
        if ( lineItemGetDto == null ) {
            return null;
        }

        LineItem.LineItemBuilder<?, ?> lineItem = LineItem.builder();

        lineItem.product( productSlimDtoToProduct( lineItemGetDto.getProduct() ) );
        lineItem.quantity( lineItemGetDto.getQuantity() );
        lineItem.unitPrice( lineItemGetDto.getUnitPrice() );
        lineItem.subTotal( lineItemGetDto.getSubTotal() );
        lineItem.particulars( lineItemGetDto.getParticulars() );
        lineItem.sale( saleSlimDtoToSale( lineItemGetDto.getSale() ) );

        return lineItem.build();
    }

    protected List<LineItem> lineItemGetDtoListToLineItemList(List<LineItemGetDto> list) {
        if ( list == null ) {
            return null;
        }

        List<LineItem> list1 = new ArrayList<LineItem>( list.size() );
        for ( LineItemGetDto lineItemGetDto : list ) {
            list1.add( lineItemGetDtoToLineItem( lineItemGetDto ) );
        }

        return list1;
    }

    protected Client clientSlimDtoToClient(ClientSlimDto clientSlimDto) {
        if ( clientSlimDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( (long) clientSlimDto.getId() );
        client.name( clientSlimDto.getName() );

        return client.build();
    }

    protected Customer customerSlimDtoToCustomer(CustomerSlimDto customerSlimDto) {
        if ( customerSlimDto == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.id( customerSlimDto.getId() );
        customer.name( customerSlimDto.getName() );

        return customer.build();
    }

    private Long saleClientId(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Client client = sale.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long saleCustomerId(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Customer customer = sale.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<PaymentPostDto> paymentListToPaymentPostDtoList(List<Payment> list) {
        if ( list == null ) {
            return null;
        }

        List<PaymentPostDto> list1 = new ArrayList<PaymentPostDto>( list.size() );
        for ( Payment payment : list ) {
            list1.add( paymentToPaymentPostDto( payment ) );
        }

        return list1;
    }

    protected Client salePostDtoToClient(SalePostDto salePostDto) {
        if ( salePostDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( salePostDto.getClient() );

        return client.build();
    }

    protected Customer salePostDtoToCustomer(SalePostDto salePostDto) {
        if ( salePostDto == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.id( salePostDto.getCustomer() );

        return customer.build();
    }

    protected List<Payment> paymentPostDtoListToPaymentList(List<PaymentPostDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Payment> list1 = new ArrayList<Payment>( list.size() );
        for ( PaymentPostDto paymentPostDto : list ) {
            list1.add( paymentDtoToEntity( paymentPostDto ) );
        }

        return list1;
    }

    private Long lineItemProductId(LineItem lineItem) {
        if ( lineItem == null ) {
            return null;
        }
        Product product = lineItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
