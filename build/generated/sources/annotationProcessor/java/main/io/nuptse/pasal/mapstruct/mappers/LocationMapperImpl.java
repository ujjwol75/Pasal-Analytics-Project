package io.nuptse.pasal.mapstruct.mappers;

import io.nuptse.pasal.mapstruct.dto.address.AddressSlimDto;
import io.nuptse.pasal.mapstruct.dto.location.LocationGetDto;
import io.nuptse.pasal.mapstruct.dto.location.LocationListDto;
import io.nuptse.pasal.mapstruct.dto.location.LocationPostDto;
import io.nuptse.pasal.mapstruct.dto.location.LocationSlimDto;
import io.nuptse.pasal.mapstruct.dto.user.UserSlimDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.auth.User;
import io.nuptse.pasal.models.core.Address;
import io.nuptse.pasal.models.core.Location;
import io.nuptse.pasal.services.AddressService;
import io.nuptse.pasal.services.UserService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:05+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @Override
    public LocationGetDto locationToLocationGetDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationGetDto.LocationGetDtoBuilder locationGetDto = LocationGetDto.builder();

        locationGetDto.addressSlimDto( addressToAddressSlimDto( location.getAddress() ) );
        locationGetDto.managerSlimDto( userToUserSlimDto( location.getManager() ) );
        Long id = locationClientId( location );
        if ( id != null ) {
            locationGetDto.client( id.intValue() );
        }
        locationGetDto.id( location.getId() );
        locationGetDto.name( location.getName() );
        locationGetDto.description( location.getDescription() );
        locationGetDto.locationNumber( location.getLocationNumber() );
        locationGetDto.updatedAt( location.getUpdatedAt() );
        locationGetDto.createdAt( location.getCreatedAt() );

        return locationGetDto.build();
    }

    @Override
    public Location locationPostDtotoLocation(LocationPostDto locationPostDto) {
        if ( locationPostDto == null ) {
            return null;
        }

        Location.LocationBuilder<?, ?> location = Location.builder();

        location.id( locationPostDto.getId() );
        location.name( locationPostDto.getName() );
        location.description( locationPostDto.getDescription() );
        location.locationNumber( locationPostDto.getLocationNumber() );
        location.address( addressService.idToEntity( locationPostDto.getAddress() ) );
        location.manager( userService.idToEntity( locationPostDto.getManager() ) );

        return location.build();
    }

    @Override
    public LocationSlimDto locationToLocationSlimDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationSlimDto locationSlimDto = new LocationSlimDto();

        locationSlimDto.setId( location.getId() );
        locationSlimDto.setName( location.getName() );

        return locationSlimDto;
    }

    @Override
    public LocationListDto locationToLocationListDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationListDto.LocationListDtoBuilder locationListDto = LocationListDto.builder();

        locationListDto.id( location.getId() );
        locationListDto.name( location.getName() );

        return locationListDto.build();
    }

    protected AddressSlimDto addressToAddressSlimDto(Address address) {
        if ( address == null ) {
            return null;
        }

        Long id = null;
        String address1 = null;

        id = address.getId();
        address1 = address.getAddress();

        AddressSlimDto addressSlimDto = new AddressSlimDto( id, address1 );

        return addressSlimDto;
    }

    protected UserSlimDto userToUserSlimDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserSlimDto.UserSlimDtoBuilder userSlimDto = UserSlimDto.builder();

        userSlimDto.id( user.getId() );
        userSlimDto.email( user.getEmail() );

        return userSlimDto.build();
    }

    private Long locationClientId(Location location) {
        if ( location == null ) {
            return null;
        }
        Client client = location.getClient();
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
