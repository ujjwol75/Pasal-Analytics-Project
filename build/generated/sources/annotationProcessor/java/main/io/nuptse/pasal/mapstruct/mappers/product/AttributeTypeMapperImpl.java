package io.nuptse.pasal.mapstruct.mappers.product;

import io.nuptse.pasal.mapstruct.dto.product.attribute.AttributeSlimDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.type.AttributeTypeGetDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.type.AttributeTypeListDto;
import io.nuptse.pasal.mapstruct.dto.product.attribute.type.AttributeTypeSlimDto;
import io.nuptse.pasal.models.product.Attribute;
import io.nuptse.pasal.models.product.AttributeType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:05+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class AttributeTypeMapperImpl implements AttributeTypeMapper {

    @Override
    public AttributeTypeGetDto attributeTypeToAttributeTypeGetDto(AttributeType attributeType) {
        if ( attributeType == null ) {
            return null;
        }

        AttributeTypeGetDto.AttributeTypeGetDtoBuilder attributeTypeGetDto = AttributeTypeGetDto.builder();

        attributeTypeGetDto.id( attributeType.getId() );
        attributeTypeGetDto.name( attributeType.getName() );
        attributeTypeGetDto.description( attributeType.getDescription() );
        attributeTypeGetDto.attribute( attributeToAttributeSlimDto( attributeType.getAttribute() ) );
        attributeTypeGetDto.updatedAt( attributeType.getUpdatedAt() );
        attributeTypeGetDto.deleted( attributeType.getDeleted() );
        attributeTypeGetDto.active( attributeType.getActive() );

        return attributeTypeGetDto.build();
    }

    @Override
    public AttributeTypeListDto attributeTypeToAttributeTypeListDto(AttributeType attributeType) {
        if ( attributeType == null ) {
            return null;
        }

        AttributeTypeListDto attributeTypeListDto = new AttributeTypeListDto();

        attributeTypeListDto.setId( attributeType.getId() );
        attributeTypeListDto.setName( attributeType.getName() );

        return attributeTypeListDto;
    }

    @Override
    public AttributeTypeSlimDto toAttributeTypeSlimDto(AttributeType attributeType) {
        if ( attributeType == null ) {
            return null;
        }

        AttributeTypeSlimDto attributeTypeSlimDto = new AttributeTypeSlimDto();

        attributeTypeSlimDto.setId( attributeType.getId() );
        attributeTypeSlimDto.setName( attributeType.getName() );

        return attributeTypeSlimDto;
    }

    protected AttributeSlimDto attributeToAttributeSlimDto(Attribute attribute) {
        if ( attribute == null ) {
            return null;
        }

        AttributeSlimDto attributeSlimDto = new AttributeSlimDto();

        attributeSlimDto.setId( attribute.getId() );
        attributeSlimDto.setName( attribute.getName() );

        return attributeSlimDto;
    }
}
