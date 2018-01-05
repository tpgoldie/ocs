package com.tpg.ocs.client;

public interface OcsAnonymousUser {
    String OCS_ANONYMOUS_USER = "ocs_anon_user";

    String OCS_ANONYMOUS_PASSWORD = "ocs_new_customer";

    default String getOcsAnonymousUsername() { return OCS_ANONYMOUS_USER; }

    default String getOcsAnonymousPassword() { return OCS_ANONYMOUS_PASSWORD; }
}
