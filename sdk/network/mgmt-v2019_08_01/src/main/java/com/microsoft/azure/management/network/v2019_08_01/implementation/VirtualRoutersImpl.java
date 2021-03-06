/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * def
 */

package com.microsoft.azure.management.network.v2019_08_01.implementation;

import com.microsoft.azure.arm.resources.collection.implementation.GroupableResourcesCoreImpl;
import com.microsoft.azure.management.network.v2019_08_01.VirtualRouters;
import com.microsoft.azure.management.network.v2019_08_01.VirtualRouter;
import rx.Observable;
import rx.Completable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import com.microsoft.azure.arm.resources.ResourceUtilsCore;
import com.microsoft.azure.arm.utils.RXMapper;
import rx.functions.Func1;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.Page;

class VirtualRoutersImpl extends GroupableResourcesCoreImpl<VirtualRouter, VirtualRouterImpl, VirtualRouterInner, VirtualRoutersInner, NetworkManager>  implements VirtualRouters {
    protected VirtualRoutersImpl(NetworkManager manager) {
        super(manager.inner().virtualRouters(), manager);
    }

    @Override
    protected Observable<VirtualRouterInner> getInnerAsync(String resourceGroupName, String name) {
        VirtualRoutersInner client = this.inner();
        return client.getByResourceGroupAsync(resourceGroupName, name);
    }

    @Override
    protected Completable deleteInnerAsync(String resourceGroupName, String name) {
        VirtualRoutersInner client = this.inner();
        return client.deleteAsync(resourceGroupName, name).toCompletable();
    }

    @Override
    public Observable<String> deleteByIdsAsync(Collection<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Observable.empty();
        }
        Collection<Observable<String>> observables = new ArrayList<>();
        for (String id : ids) {
            final String resourceGroupName = ResourceUtilsCore.groupFromResourceId(id);
            final String name = ResourceUtilsCore.nameFromResourceId(id);
            Observable<String> o = RXMapper.map(this.inner().deleteAsync(resourceGroupName, name), id);
            observables.add(o);
        }
        return Observable.mergeDelayError(observables);
    }

    @Override
    public Observable<String> deleteByIdsAsync(String...ids) {
        return this.deleteByIdsAsync(new ArrayList<String>(Arrays.asList(ids)));
    }

    @Override
    public void deleteByIds(Collection<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            this.deleteByIdsAsync(ids).toBlocking().last();
        }
    }

    @Override
    public void deleteByIds(String...ids) {
        this.deleteByIds(new ArrayList<String>(Arrays.asList(ids)));
    }

    @Override
    public PagedList<VirtualRouter> listByResourceGroup(String resourceGroupName) {
        VirtualRoutersInner client = this.inner();
        return this.wrapList(client.listByResourceGroup(resourceGroupName));
    }

    @Override
    public Observable<VirtualRouter> listByResourceGroupAsync(String resourceGroupName) {
        VirtualRoutersInner client = this.inner();
        return client.listByResourceGroupAsync(resourceGroupName)
        .flatMapIterable(new Func1<Page<VirtualRouterInner>, Iterable<VirtualRouterInner>>() {
            @Override
            public Iterable<VirtualRouterInner> call(Page<VirtualRouterInner> page) {
                return page.items();
            }
        })
        .map(new Func1<VirtualRouterInner, VirtualRouter>() {
            @Override
            public VirtualRouter call(VirtualRouterInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public PagedList<VirtualRouter> list() {
        VirtualRoutersInner client = this.inner();
        return this.wrapList(client.list());
    }

    @Override
    public Observable<VirtualRouter> listAsync() {
        VirtualRoutersInner client = this.inner();
        return client.listAsync()
        .flatMapIterable(new Func1<Page<VirtualRouterInner>, Iterable<VirtualRouterInner>>() {
            @Override
            public Iterable<VirtualRouterInner> call(Page<VirtualRouterInner> page) {
                return page.items();
            }
        })
        .map(new Func1<VirtualRouterInner, VirtualRouter>() {
            @Override
            public VirtualRouter call(VirtualRouterInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public VirtualRouterImpl define(String name) {
        return wrapModel(name);
    }

    @Override
    protected VirtualRouterImpl wrapModel(VirtualRouterInner inner) {
        return  new VirtualRouterImpl(inner.name(), inner, manager());
    }

    @Override
    protected VirtualRouterImpl wrapModel(String name) {
        return new VirtualRouterImpl(name, new VirtualRouterInner(), this.manager());
    }

}
