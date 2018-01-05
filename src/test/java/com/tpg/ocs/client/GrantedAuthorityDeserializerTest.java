package com.tpg.ocs.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

import static com.tpg.ocs.domain.UserRole.NEW_CUSTOMER_ROLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GrantedAuthorityDeserializerTest {

    @Mock
    private JsonParser jsonParser;

    @Mock
    private ObjectCodec objectCodec;

    @Mock
    private JsonNode jsonNode;

    private GrantedAuthorityDeserializer deserializer;

    @Before
    public void setUp() {
        deserializer = new GrantedAuthorityDeserializer();
    }

    @Test
    public void deserialize() throws IOException {

        when(jsonParser.getCodec()).thenReturn(objectCodec);

        when(objectCodec.readTree(jsonParser)).thenReturn(jsonNode);

        when(jsonNode.get("authority")).thenReturn(jsonNode);

        when(jsonNode.asText()).thenReturn(NEW_CUSTOMER_ROLE.getLabel());

        SimpleGrantedAuthority actual = (SimpleGrantedAuthority) deserializer.deserialize(jsonParser, null);

        assertThat(actual.getAuthority()).isEqualTo(NEW_CUSTOMER_ROLE.getLabel());
    }
}
