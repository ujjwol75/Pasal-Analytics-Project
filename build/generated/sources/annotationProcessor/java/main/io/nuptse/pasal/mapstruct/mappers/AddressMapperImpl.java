package io.nuptse.pasal.mapstruct.mappers;

import io.nuptse.pasal.mapstruct.dto.address.AddressGetDto;
import io.nuptse.pasal.mapstruct.dto.address.AddressListDto;
import io.nuptse.pasal.mapstruct.dto.address.AddressPostDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.core.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressGetDto addressToAddressGetDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressGetDto.AddressGetDtoBuilder addressGetDto = AddressGetDto.builder();

        Long id = addressClientId( address );
        if ( id != null ) {
            addressGetDto.client( id.intValue() );
        }
        addressGetDto.id( address.getId() );
        addressGetDto.address( address.getAddress() );
        addressGetDto.address2( address.getAddress2() );
        addressGetDto.city( address.getCity() );
        addressGetDto.stateOrProvince( address.getStateOrProvince() );
        addressGetDto.postalCode( address.getPostalCode() );
        addressGetDto.country( address.getCountry() );
        addressGetDto.description( address.getDescription() );

        return addressGetDto.build();
    }

    @Override
    public Address addressPostDtoToAddress(AddressPostDto addressPostDto) {
        if ( addressPostDto == null ) {
            return null;
        }

        Address.AddressBuilder<?, ?> address = Address.builder();

        address.id( addressPostDto.id() );
        address.address( addressPostDto.address() );
        address.address2( addressPostDto.address2() );
        address.city( addressPostDto.city() );
        address.stateOrProvince( addressPostDto.stateOrProvince() );
        address.postalCode( addressPostDto.postalCode() );
        address.country( addressPostDto.country() );
        address.description( addressPostDto.description() );

        return address.build();
    }

    @Override
    public AddressListDto addressToAddressListDto(Address address) {
        if ( address == null ) {
            return null;
        }

        Long id = null;
        String address1 = null;

        id = address.getId();
        address1 = address.getAddress();

        AddressListDto addressListDto = new AddressListDto( id, address1 );

        return addressListDto;
    }

    private Long addressClientId(Address address) {
        if ( address == null ) {
            return null;
        }
        Client client = address.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
