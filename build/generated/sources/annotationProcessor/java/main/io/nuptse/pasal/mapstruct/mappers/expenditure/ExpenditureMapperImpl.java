package io.nuptse.pasal.mapstruct.mappers.expenditure;

import io.nuptse.pasal.mapstruct.dto.client.ClientSlimDto;
import io.nuptse.pasal.mapstruct.dto.customer.CustomerSlimDto;
import io.nuptse.pasal.mapstruct.dto.expenditure.ExpenditureGetDto;
import io.nuptse.pasal.mapstruct.dto.expenditure.ExpenditureListDto;
import io.nuptse.pasal.mapstruct.dto.expenditure.ExpenditurePostDto;
import io.nuptse.pasal.mapstruct.dto.expenditure.ExpenditureSlimDto;
import io.nuptse.pasal.mapstruct.dto.expenditure.lineitem.LineItemGetDto;
import io.nuptse.pasal.mapstruct.dto.expenditure.lineitem.LineItemPostDto;
import io.nuptse.pasal.mapstruct.dto.product.ProductSlimDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.Customer;
import io.nuptse.pasal.models.expenditure.Expenditure;
import io.nuptse.pasal.models.expenditure.LineItemExpenditure;
import io.nuptse.pasal.models.product.Product;
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
public class ExpenditureMapperImpl implements ExpenditureMapper {

    @Override
    public ExpenditureGetDto expenditureToExpenditureGetDto(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }

        ExpenditureGetDto.ExpenditureGetDtoBuilder expenditureGetDto = ExpenditureGetDto.builder();

        expenditureGetDto.items( lineItemExpenditureListToLineItemGetDtoList( expenditure.getLineItems() ) );
        expenditureGetDto.id( expenditure.getId() );
        expenditureGetDto.total( expenditure.getTotal() );
        expenditureGetDto.partialAmount( expenditure.getPartialAmount() );
        expenditureGetDto.discount( expenditure.getDiscount() );
        expenditureGetDto.subTotal( expenditure.getSubTotal() );
        expenditureGetDto.grandTotal( expenditure.getGrandTotal() );
        expenditureGetDto.isVoid( expenditure.getIsVoid() );
        expenditureGetDto.isCredit( expenditure.getIsCredit() );
        expenditureGetDto.billAt( expenditure.getBillAt() );
        expenditureGetDto.client( clientToClientSlimDto( expenditure.getClient() ) );
        expenditureGetDto.customer( customerToCustomerSlimDto( expenditure.getCustomer() ) );
        expenditureGetDto.remarks( expenditure.getRemarks() );

        return expenditureGetDto.build();
    }

    @Override
    public Expenditure toEntity(ExpenditureGetDto expenditureGetDto) {
        if ( expenditureGetDto == null ) {
            return null;
        }

        Expenditure.ExpenditureBuilder<?, ?> expenditure = Expenditure.builder();

        expenditure.lineItems( lineItemGetDtoListToLineItemExpenditureList( expenditureGetDto.getItems() ) );
        expenditure.id( expenditureGetDto.getId() );
        expenditure.total( expenditureGetDto.getTotal() );
        expenditure.partialAmount( expenditureGetDto.getPartialAmount() );
        expenditure.discount( expenditureGetDto.getDiscount() );
        expenditure.subTotal( expenditureGetDto.getSubTotal() );
        expenditure.grandTotal( expenditureGetDto.getGrandTotal() );
        expenditure.remarks( expenditureGetDto.getRemarks() );
        expenditure.isVoid( expenditureGetDto.getIsVoid() );
        expenditure.isCredit( expenditureGetDto.getIsCredit() );
        expenditure.billAt( expenditureGetDto.getBillAt() );
        expenditure.client( clientSlimDtoToClient( expenditureGetDto.getClient() ) );
        expenditure.customer( customerSlimDtoToCustomer( expenditureGetDto.getCustomer() ) );

        return expenditure.build();
    }

    @Override
    public ExpenditurePostDto expenditureToExpenditurePostDto(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }

        ExpenditurePostDto.ExpenditurePostDtoBuilder expenditurePostDto = ExpenditurePostDto.builder();

        expenditurePostDto.client( expenditureClientId( expenditure ) );
        expenditurePostDto.customer( expenditureCustomerId( expenditure ) );
        expenditurePostDto.id( expenditure.getId() );
        expenditurePostDto.total( expenditure.getTotal() );
        expenditurePostDto.partialAmount( expenditure.getPartialAmount() );
        expenditurePostDto.discount( expenditure.getDiscount() );
        expenditurePostDto.subTotal( expenditure.getSubTotal() );
        expenditurePostDto.grandTotal( expenditure.getGrandTotal() );
        expenditurePostDto.isVoid( expenditure.getIsVoid() );
        expenditurePostDto.isCredit( expenditure.getIsCredit() );
        expenditurePostDto.billAt( expenditure.getBillAt() );
        expenditurePostDto.remarks( expenditure.getRemarks() );

        return expenditurePostDto.build();
    }

    @Override
    public Expenditure expenditurePostDtoToExpenditure(ExpenditurePostDto expenditurePostDto) {
        if ( expenditurePostDto == null ) {
            return null;
        }

        Expenditure.ExpenditureBuilder<?, ?> expenditure = Expenditure.builder();

        expenditure.client( expenditurePostDtoToClient( expenditurePostDto ) );
        expenditure.customer( expenditurePostDtoToCustomer( expenditurePostDto ) );
        expenditure.id( expenditurePostDto.getId() );
        expenditure.total( expenditurePostDto.getTotal() );
        expenditure.partialAmount( expenditurePostDto.getPartialAmount() );
        expenditure.discount( expenditurePostDto.getDiscount() );
        expenditure.subTotal( expenditurePostDto.getSubTotal() );
        expenditure.grandTotal( expenditurePostDto.getGrandTotal() );
        expenditure.remarks( expenditurePostDto.getRemarks() );
        expenditure.isVoid( expenditurePostDto.getIsVoid() );
        expenditure.isCredit( expenditurePostDto.getIsCredit() );
        expenditure.billAt( expenditurePostDto.getBillAt() );

        return expenditure.build();
    }

    @Override
    public List<ExpenditureListDto> expendituresToExpenditureListDtos(List<Expenditure> expenditures) {
        if ( expenditures == null ) {
            return null;
        }

        List<ExpenditureListDto> list = new ArrayList<ExpenditureListDto>( expenditures.size() );
        for ( Expenditure expenditure : expenditures ) {
            list.add( expenditureToExpenditureListDto( expenditure ) );
        }

        return list;
    }

    @Override
    public LineItemPostDto lineItemToLineItemPostDto(LineItemExpenditure lineItem) {
        if ( lineItem == null ) {
            return null;
        }

        LineItemPostDto.LineItemPostDtoBuilder lineItemPostDto = LineItemPostDto.builder();

        lineItemPostDto.product( lineItemProductId( lineItem ) );
        lineItemPostDto.quantity( lineItem.getQuantity() );
        lineItemPostDto.unitPrice( lineItem.getUnitPrice() );
        lineItemPostDto.subTotal( lineItem.getSubTotal() );
        lineItemPostDto.particulars( lineItem.getParticulars() );
        lineItemPostDto.expenditure( expenditureToExpenditurePostDto( lineItem.getExpenditure() ) );

        return lineItemPostDto.build();
    }

    @Override
    public List<ExpenditureGetDto> expendituresToExpenditureAllDtos(List<ExpenditureGetDto> expenditures) {
        if ( expenditures == null ) {
            return null;
        }

        List<ExpenditureGetDto> list = new ArrayList<ExpenditureGetDto>( expenditures.size() );
        for ( ExpenditureGetDto expenditureGetDto : expenditures ) {
            list.add( expenditureGetDto );
        }

        return list;
    }

    @Override
    public ExpenditureListDto expenditureToExpenditureListDto(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }

        ExpenditureListDto.ExpenditureListDtoBuilder expenditureListDto = ExpenditureListDto.builder();

        if ( expenditure.getId() != null ) {
            expenditureListDto.id( expenditure.getId().intValue() );
        }
        expenditureListDto.customer( expenditure.getCustomer() );

        return expenditureListDto.build();
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

    protected ExpenditureSlimDto expenditureToExpenditureSlimDto(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }

        ExpenditureSlimDto expenditureSlimDto = new ExpenditureSlimDto();

        if ( expenditure.getId() != null ) {
            expenditureSlimDto.setId( expenditure.getId() );
        }

        return expenditureSlimDto;
    }

    protected LineItemGetDto lineItemExpenditureToLineItemGetDto(LineItemExpenditure lineItemExpenditure) {
        if ( lineItemExpenditure == null ) {
            return null;
        }

        LineItemGetDto.LineItemGetDtoBuilder lineItemGetDto = LineItemGetDto.builder();

        lineItemGetDto.product( productToProductSlimDto( lineItemExpenditure.getProduct() ) );
        lineItemGetDto.quantity( lineItemExpenditure.getQuantity() );
        lineItemGetDto.unitPrice( lineItemExpenditure.getUnitPrice() );
        lineItemGetDto.subTotal( lineItemExpenditure.getSubTotal() );
        lineItemGetDto.particulars( lineItemExpenditure.getParticulars() );
        lineItemGetDto.expenditure( expenditureToExpenditureSlimDto( lineItemExpenditure.getExpenditure() ) );

        return lineItemGetDto.build();
    }

    protected List<LineItemGetDto> lineItemExpenditureListToLineItemGetDtoList(List<LineItemExpenditure> list) {
        if ( list == null ) {
            return null;
        }

        List<LineItemGetDto> list1 = new ArrayList<LineItemGetDto>( list.size() );
        for ( LineItemExpenditure lineItemExpenditure : list ) {
            list1.add( lineItemExpenditureToLineItemGetDto( lineItemExpenditure ) );
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

    protected Product productSlimDtoToProduct(ProductSlimDto productSlimDto) {
        if ( productSlimDto == null ) {
            return null;
        }

        Product.ProductBuilder<?, ?> product = Product.builder();

        product.id( productSlimDto.getId() );
        product.name( productSlimDto.getName() );

        return product.build();
    }

    protected Expenditure expenditureSlimDtoToExpenditure(ExpenditureSlimDto expenditureSlimDto) {
        if ( expenditureSlimDto == null ) {
            return null;
        }

        Expenditure.ExpenditureBuilder<?, ?> expenditure = Expenditure.builder();

        expenditure.id( expenditureSlimDto.getId() );

        return expenditure.build();
    }

    protected LineItemExpenditure lineItemGetDtoToLineItemExpenditure(LineItemGetDto lineItemGetDto) {
        if ( lineItemGetDto == null ) {
            return null;
        }

        LineItemExpenditure.LineItemExpenditureBuilder<?, ?> lineItemExpenditure = LineItemExpenditure.builder();

        lineItemExpenditure.product( productSlimDtoToProduct( lineItemGetDto.getProduct() ) );
        lineItemExpenditure.quantity( lineItemGetDto.getQuantity() );
        lineItemExpenditure.unitPrice( lineItemGetDto.getUnitPrice() );
        lineItemExpenditure.subTotal( lineItemGetDto.getSubTotal() );
        lineItemExpenditure.particulars( lineItemGetDto.getParticulars() );
        lineItemExpenditure.expenditure( expenditureSlimDtoToExpenditure( lineItemGetDto.getExpenditure() ) );

        return lineItemExpenditure.build();
    }

    protected List<LineItemExpenditure> lineItemGetDtoListToLineItemExpenditureList(List<LineItemGetDto> list) {
        if ( list == null ) {
            return null;
        }

        List<LineItemExpenditure> list1 = new ArrayList<LineItemExpenditure>( list.size() );
        for ( LineItemGetDto lineItemGetDto : list ) {
            list1.add( lineItemGetDtoToLineItemExpenditure( lineItemGetDto ) );
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

    private Long expenditureClientId(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }
        Client client = expenditure.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long expenditureCustomerId(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }
        Customer customer = expenditure.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Client expenditurePostDtoToClient(ExpenditurePostDto expenditurePostDto) {
        if ( expenditurePostDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( expenditurePostDto.getClient() );

        return client.build();
    }

    protected Customer expenditurePostDtoToCustomer(ExpenditurePostDto expenditurePostDto) {
        if ( expenditurePostDto == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.id( expenditurePostDto.getCustomer() );

        return customer.build();
    }

    private Long lineItemProductId(LineItemExpenditure lineItemExpenditure) {
        if ( lineItemExpenditure == null ) {
            return null;
        }
        Product product = lineItemExpenditure.getProduct();
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
