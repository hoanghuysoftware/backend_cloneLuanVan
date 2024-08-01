package com.software.service;

import com.software.dto.PurchaseOrderDTO;
import com.software.model.PurchaseOrder;

public interface PurchaseOrderService {
    PurchaseOrder createNewPurchaseOrder(PurchaseOrderDTO purchaseOrder);
}
