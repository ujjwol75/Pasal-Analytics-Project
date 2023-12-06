package io.nuptse.pasal.mapstruct.mappers;

import io.nuptse.pasal.mapstruct.dto.client.ClientSlimDto;
import io.nuptse.pasal.mapstruct.dto.user.UserListDto;
import io.nuptse.pasal.mapstruct.dto.user.UserPostDto;
import io.nuptse.pasal.mapstruct.dto.user.UserResponseDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.auth.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:05+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.firstname( user.getFirstname() );
        userResponseDto.lastname( user.getLastname() );
        userResponseDto.email( user.getEmail() );
        userResponseDto.password( user.getPassword() );
        if ( user.getRole() != null ) {
            userResponseDto.role( user.getRole().name() );
        }
        userResponseDto.client( clientToClientSlimDto( user.getClient() ) );

        return userResponseDto.build();
    }

    @Override
    public List<UserPostDto> usersToUserAllDtos(List<UserPostDto> users) {
        if ( users == null ) {
            return null;
        }

        List<UserPostDto> list = new ArrayList<UserPostDto>( users.size() );
        for ( UserPostDto userPostDto : users ) {
            list.add( userPostDto );
        }

        return list;
    }

    @Override
    public UserListDto userToUserListDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserListDto.UserListDtoBuilder userListDto = UserListDto.builder();

        userListDto.id( user.getId() );
        userListDto.email( user.getEmail() );

        return userListDto.build();
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
