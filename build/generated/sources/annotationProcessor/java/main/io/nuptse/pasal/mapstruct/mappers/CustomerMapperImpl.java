package io.nuptse.pasal.mapstruct.mappers;

import io.nuptse.pasal.mapstruct.dto.client.ClientSlimDto;
import io.nuptse.pasal.mapstruct.dto.customer.CustomerGetDto;
import io.nuptse.pasal.mapstruct.dto.customer.CustomerListDto;
import io.nuptse.pasal.mapstruct.dto.customer.CustomerPostDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.Customer;
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
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerGetDto customerToCustomerGetDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerGetDto.CustomerGetDtoBuilder customerGetDto = CustomerGetDto.builder();

        if ( customer.getId() != null ) {
            customerGetDto.id( customer.getId().intValue() );
        }
        customerGetDto.name( customer.getName() );
        customerGetDto.description( customer.getDescription() );
        customerGetDto.phone( customer.getPhone() );
        customerGetDto.address( customer.getAddress() );
        customerGetDto.email( customer.getEmail() );
        customerGetDto.client( clientToClientSlimDto( customer.getClient() ) );

        return customerGetDto.build();
    }

    @Override
    public CustomerPostDto customerToCustomerPostDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerPostDto customerPostDto = new CustomerPostDto();

        if ( customer.getId() != null ) {
            customerPostDto.setId( String.valueOf( customer.getId() ) );
        }
        customerPostDto.setName( customer.getName() );
        customerPostDto.setDescription( customer.getDescription() );
        customerPostDto.setPhone( customer.getPhone() );
        customerPostDto.setAddress( customer.getAddress() );
        customerPostDto.setEmail( customer.getEmail() );

        return customerPostDto;
    }

    @Override
    public Customer customerPostDtoToCustomer(CustomerPostDto customerPostDto) {
        if ( customerPostDto == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        if ( customerPostDto.getId() != null ) {
            customer.id( Long.parseLong( customerPostDto.getId() ) );
        }
        customer.name( customerPostDto.getName() );
        customer.description( customerPostDto.getDescription() );
        customer.phone( customerPostDto.getPhone() );
        customer.address( customerPostDto.getAddress() );
        customer.email( customerPostDto.getEmail() );

        return customer.build();
    }

    @Override
    public Customer customerGetDtoToCustomer(CustomerGetDto customerGetDto) {
        if ( customerGetDto == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.id( (long) customerGetDto.getId() );
        customer.name( customerGetDto.getName() );
        customer.description( customerGetDto.getDescription() );
        customer.phone( customerGetDto.getPhone() );
        customer.address( customerGetDto.getAddress() );
        customer.email( customerGetDto.getEmail() );
        customer.client( clientSlimDtoToClient( customerGetDto.getClient() ) );

        return customer.build();
    }

    @Override
    public List<CustomerGetDto> customersToCustomerAllDtos(List<CustomerGetDto> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerGetDto> list = new ArrayList<CustomerGetDto>( customers.size() );
        for ( CustomerGetDto customerGetDto : customers ) {
            list.add( customerGetDto );
        }

        return list;
    }

    @Override
    public CustomerListDto customerToCustomerListDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerListDto.CustomerListDtoBuilder customerListDto = CustomerListDto.builder();

        if ( customer.getId() != null ) {
            customerListDto.id( customer.getId().intValue() );
        }
        customerListDto.name( customer.getName() );

        return customerListDto.build();
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

    protected Client clientSlimDtoToClient(ClientSlimDto clientSlimDto) {
        if ( clientSlimDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( (long) clientSlimDto.getId() );
        client.name( clientSlimDto.getName() );

        return client.build();
    }
}
