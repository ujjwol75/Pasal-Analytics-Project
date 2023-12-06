package io.nuptse.pasal.mapstruct.mappers;

import io.nuptse.pasal.mapstruct.dto.client.ClientSlimDto;
import io.nuptse.pasal.mapstruct.dto.vendor.VendorGetDto;
import io.nuptse.pasal.mapstruct.dto.vendor.VendorListDto;
import io.nuptse.pasal.mapstruct.dto.vendor.VendorPostDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.Vendor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class VendorMapperImpl implements VendorMapper {

    @Override
    public VendorGetDto vendorToVendorGetDto(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorGetDto.VendorGetDtoBuilder vendorGetDto = VendorGetDto.builder();

        if ( vendor.getId() != null ) {
            vendorGetDto.id( vendor.getId().intValue() );
        }
        vendorGetDto.name( vendor.getName() );
        vendorGetDto.description( vendor.getDescription() );
        vendorGetDto.phone( vendor.getPhone() );
        vendorGetDto.address( vendor.getAddress() );
        vendorGetDto.email( vendor.getEmail() );
        vendorGetDto.client( clientToClientSlimDto( vendor.getClient() ) );

        return vendorGetDto.build();
    }

    @Override
    public Vendor vendorPostDtoToVendor(VendorPostDto vendorPostDto) {
        if ( vendorPostDto == null ) {
            return null;
        }

        Vendor.VendorBuilder<?, ?> vendor = Vendor.builder();

        vendor.id( vendorPostDto.getId() );
        vendor.name( vendorPostDto.getName() );
        vendor.description( vendorPostDto.getDescription() );
        vendor.address( vendorPostDto.getAddress() );
        vendor.phone( vendorPostDto.getPhone() );
        vendor.email( vendorPostDto.getEmail() );

        return vendor.build();
    }

    @Override
    public VendorListDto vendorToVendorListDto(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        VendorListDto.VendorListDtoBuilder vendorListDto = VendorListDto.builder();

        if ( vendor.getId() != null ) {
            vendorListDto.id( vendor.getId().intValue() );
        }
        vendorListDto.name( vendor.getName() );

        return vendorListDto.build();
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
}
