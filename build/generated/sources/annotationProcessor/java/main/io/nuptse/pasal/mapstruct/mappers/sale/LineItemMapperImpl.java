package io.nuptse.pasal.mapstruct.mappers.sale;

import io.nuptse.pasal.mapstruct.mappers.ReferenceMapper;
import io.nuptse.pasal.models.sale.LineItem;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class LineItemMapperImpl implements LineItemMapper {

    @Autowired
    private ReferenceMapper referenceMapper;

    @Override
    public LineItem toEntity(Long id) {
        if ( id == null ) {
            return null;
        }

        LineItem.LineItemBuilder<?, ?> lineItem = referenceMapper.map( id, LineItem.LineItemBuilder.class );

        lineItem.id( id );

        return lineItem.build();
    }
}
