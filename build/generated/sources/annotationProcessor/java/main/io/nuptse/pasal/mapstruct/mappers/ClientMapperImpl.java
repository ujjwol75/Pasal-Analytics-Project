package io.nuptse.pasal.mapstruct.mappers;

import io.nuptse.pasal.mapstruct.dto.client.ClientGetDto;
import io.nuptse.pasal.mapstruct.dto.client.ClientListDto;
import io.nuptse.pasal.mapstruct.dto.client.ClientPostDto;
import io.nuptse.pasal.models.Client;
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
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientGetDto clientToClientGetDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientGetDto.ClientGetDtoBuilder clientGetDto = ClientGetDto.builder();

        clientGetDto.id( client.getId() );
        clientGetDto.name( client.getName() );
        clientGetDto.code( client.getCode() );
        clientGetDto.url( client.getUrl() );
        clientGetDto.email( client.getEmail() );
        clientGetDto.displayName( client.getDisplayName() );
        clientGetDto.phone( client.getPhone() );
        clientGetDto.vat( client.getVat() );
        clientGetDto.pan( client.getPan() );
        clientGetDto.address( client.getAddress() );
        clientGetDto.description( client.getDescription() );

        return clientGetDto.build();
    }

    @Override
    public ClientPostDto clientToClientPostDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientPostDto clientPostDto = new ClientPostDto();

        clientPostDto.setName( client.getName() );
        clientPostDto.setCode( client.getCode() );
        clientPostDto.setUrl( client.getUrl() );
        clientPostDto.setEmail( client.getEmail() );
        clientPostDto.setDisplayName( client.getDisplayName() );
        clientPostDto.setPhone( client.getPhone() );
        clientPostDto.setVat( client.getVat() );
        clientPostDto.setPan( client.getPan() );
        clientPostDto.setAddress( client.getAddress() );
        clientPostDto.setDescription( client.getDescription() );

        return clientPostDto;
    }

    @Override
    public Client clientPostDtoToClient(ClientPostDto clientPostDto) {
        if ( clientPostDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.name( clientPostDto.getName() );
        client.code( clientPostDto.getCode() );
        client.url( clientPostDto.getUrl() );
        client.email( clientPostDto.getEmail() );
        client.displayName( clientPostDto.getDisplayName() );
        client.phone( clientPostDto.getPhone() );
        client.vat( clientPostDto.getVat() );
        client.pan( clientPostDto.getPan() );
        client.address( clientPostDto.getAddress() );
        client.description( clientPostDto.getDescription() );

        return client.build();
    }

    @Override
    public Client clientGetDtoToClient(ClientGetDto clientGetDto) {
        if ( clientGetDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( clientGetDto.getId() );
        client.name( clientGetDto.getName() );
        client.code( clientGetDto.getCode() );
        client.url( clientGetDto.getUrl() );
        client.email( clientGetDto.getEmail() );
        client.displayName( clientGetDto.getDisplayName() );
        client.phone( clientGetDto.getPhone() );
        client.vat( clientGetDto.getVat() );
        client.pan( clientGetDto.getPan() );
        client.address( clientGetDto.getAddress() );
        client.description( clientGetDto.getDescription() );

        return client.build();
    }

    @Override
    public List<ClientGetDto> clientsToClientGetDtos(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientGetDto> list = new ArrayList<ClientGetDto>( clients.size() );
        for ( Client client : clients ) {
            list.add( clientToClientGetDto( client ) );
        }

        return list;
    }

    @Override
    public ClientListDto clientToClientListDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientListDto.ClientListDtoBuilder clientListDto = ClientListDto.builder();

        if ( client.getId() != null ) {
            clientListDto.id( client.getId().intValue() );
        }
        clientListDto.name( client.getName() );

        return clientListDto.build();
    }
}
