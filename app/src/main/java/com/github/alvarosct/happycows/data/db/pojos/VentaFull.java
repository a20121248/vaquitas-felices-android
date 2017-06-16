package com.github.alvarosct.happycows.data.db.pojos;

import com.github.alvarosct.happycows.data.db.models.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class VentaFull extends BaseModel {

    @SerializedName("bioferia_id")
    private int bioferiaId;
    @SerializedName("client_id")
    private int clientId;
    @SerializedName("seller_id")
    private int vendedorId = 1;

    @SerializedName("productos")
    List<ProductoItem> productoItemList;

    public VentaFull(int bioferiaId, int clientId) {
        this.bioferiaId = bioferiaId;
        this.clientId = clientId;
    }

    public int getBioferiaId() {
        return bioferiaId;
    }

    public void setBioferiaId(int bioferiaId) {
        this.bioferiaId = bioferiaId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public List<ProductoItem> getProductoItemList() {
        return productoItemList;
    }

    public void setProductoItemList(List<ProductoItem> productoItemList) {
        this.productoItemList = productoItemList;
    }
}