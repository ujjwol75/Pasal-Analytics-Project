package io.nuptse.pasal.mapstruct.mappers.expenditure;

import io.nuptse.pasal.mapstruct.mappers.ReferenceMapper;
import io.nuptse.pasal.models.expenditure.LineItemExpenditure;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T17:09:06+0545",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class LineItemExpenditureMapperImpl implements LineItemExpenditureMapper {

    @Autowired
    private ReferenceMapper referenceMapper;

    @Override
    public LineItemExpenditure toEntity(Long id) {
        if ( id == null ) {
            return null;
        }

        LineItemExpenditure.LineItemExpenditureBuilder<?, ?> lineItemExpenditure = referenceMapper.map( id, LineItemExpenditure.LineItemExpenditureBuilder.class );

        lineItemExpenditure.id( id );

        return lineItemExpenditure.build();
    }
}
