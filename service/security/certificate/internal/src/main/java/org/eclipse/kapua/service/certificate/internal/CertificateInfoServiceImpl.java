/*******************************************************************************
 * Copyright (c) 2018 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.certificate.internal;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.config.metatype.KapuaTocd;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.certificate.CertificateGenerator;
import org.eclipse.kapua.service.certificate.CertificateUsage;
import org.eclipse.kapua.service.certificate.CertificateQuery;
import org.eclipse.kapua.service.certificate.CertificateService;
import org.eclipse.kapua.service.certificate.CertificateInfo;
import org.eclipse.kapua.service.certificate.CertificateInfoCreator;
import org.eclipse.kapua.service.certificate.CertificateInfoListResult;
import org.eclipse.kapua.service.certificate.CertificateInfoQuery;
import org.eclipse.kapua.service.certificate.CertificateInfoService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@KapuaProvider
public class CertificateInfoServiceImpl implements CertificateInfoService {

    private static final KapuaLocator LOCATOR = KapuaLocator.getInstance();
    private static final CertificateService PRIVATE_CERTIFICATE_SERVICE = LOCATOR.getService(CertificateService.class);

    @Override
    public CertificateInfo create(CertificateInfoCreator creator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CertificateInfo find(KapuaId scopeId, KapuaId entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CertificateInfoListResult query(KapuaQuery<CertificateInfo> query) throws KapuaException {

        CertificateQuery privateQuery = new CertificateQueryImpl(query);
        privateQuery.setIncludeInherited(((CertificateInfoQuery) query).getIncludeInherited());

        CertificateInfoListResult publicCertificates = new CertificateInfoListResultImpl();
        publicCertificates.addItem(PRIVATE_CERTIFICATE_SERVICE.query(privateQuery).getFirstItem());

        return publicCertificates;
    }

    @Override
    public long count(KapuaQuery<CertificateInfo> query) throws KapuaException {

        CertificateQuery privateQuery = new CertificateQueryImpl(query);
        privateQuery.setIncludeInherited(((CertificateInfoQuery) query).getIncludeInherited());

        return PRIVATE_CERTIFICATE_SERVICE.count(privateQuery);
    }

    @Override
    public void delete(KapuaId scopeId, KapuaId entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CertificateInfo findByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CertificateInfo update(CertificateInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CertificateInfo generate(CertificateGenerator generator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CertificateInfo> findAncestorsCertificates(KapuaId scopeId, CertificateUsage usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public KapuaTocd getConfigMetadata(KapuaId scopeId) {
        return EmptyTocd.getInstance();
    }

    @Override
    public Map<String, Object> getConfigValues(KapuaId scopeId) {
        return Collections.emptyMap();
    }

    @Override
    public void setConfigValues(KapuaId scopeId, KapuaId parentId, Map<String, Object> values) {
        throw new UnsupportedOperationException();
    }
}