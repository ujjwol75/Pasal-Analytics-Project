package io.nuptse.pasal.mapstruct.mappers.product;

import io.nuptse.pasal.mapstruct.dto.product.attribute.AttributeGetDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.AttributeListDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.AttributePostDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.AttributeSlimDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.type.AttributeTypeListDto;
import io.nuptse.pasal.models.Client;
import io.nuptse.pasal.models.product.Attribute;
import io.nuptse.pasal.models.product.AttributeType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class AttributeMapperImpl implements AttributeMapper {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public AttributeGetDto attributeToAttributeGetDto(Attribute attribute) {
        if ( attribute == null ) {
            return null;
        }

        AttributeGetDto.AttributeGetDtoBuilder attributeGetDto = AttributeGetDto.builder();

        attributeGetDto.id( attribute.getId() );
        attributeGetDto.name( attribute.getName() );
        attributeGetDto.description( attribute.getDescription() );
        attributeGetDto.updatedAt( attribute.getUpdatedAt() );
        attributeGetDto.deleted( attribute.getDeleted() );
        attributeGetDto.active( attribute.getActive() );

        return attributeGetDto.build();
    }

    @Override
    public Attribute attributePostDtoToAttribute(AttributePostDto attributePostDto) {
        if ( attributePostDto == null ) {
            return null;
        }

        Attribute.AttributeBuilder<?, ?> attribute = Attribute.builder();

        attribute.client( attributePostDtoToClient( attributePostDto ) );
        attribute.id( attributePostDto.getId() );
        attribute.name( attributePostDto.getName() );
        attribute.description( attributePostDto.getDescription() );
        attribute.active( attributePostDto.getActive() );
        attribute.deleted( attributePostDto.getDeleted() );

        return attribute.build();
    }

    @Override
    public AttributeListDto attributeToAttributeListDto(Attribute attribute) {
        if ( attribute == null ) {
            return null;
        }

        AttributeListDto.AttributeListDtoBuilder attributeListDto = AttributeListDto.builder();

        attributeListDto.id( attribute.getId() );
        attributeListDto.name( attribute.getName() );

        return attributeListDto.build();
    }

    @Override
    public AttributeSlimDto toAttributeSlimDto(Attribute attribute) {
        if ( attribute == null ) {
            return null;
        }

        AttributeSlimDto attributeSlimDto = new AttributeSlimDto();

        attributeSlimDto.setAttributeTypeListDto( attributeTypeListToAttributeTypeListDtoList( attribute.getAttributeTypes() ) );
        attributeSlimDto.setId( attribute.getId() );
        attributeSlimDto.setName( attribute.getName() );

        return attributeSlimDto;
    }

    protected Client attributePostDtoToClient(AttributePostDto attributePostDto) {
        if ( attributePostDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( attributePostDto.getClient() );

        return client.build();
    }

    protected List<AttributeTypeListDto> attributeTypeListToAttributeTypeListDtoList(List<AttributeType> list) {
        if ( list == null ) {
            return null;
        }

        List<AttributeTypeListDto> list1 = new ArrayList<AttributeTypeListDto>( list.size() );
        for ( AttributeType attributeType : list ) {
            list1.add( attributeTypeMapper.attributeTypeToAttributeTypeListDto( attributeType ) );
        }

        return list1;
    }
}
