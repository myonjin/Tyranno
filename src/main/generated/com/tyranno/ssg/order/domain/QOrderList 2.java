package com.tyranno.ssg.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderList is a Querydsl query type for OrderList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderList extends EntityPathBase<OrderList> {

    private static final long serialVersionUID = 802912585L;

    public static final QOrderList orderList = new QOrderList("orderList");

    public final StringPath deliveryBase = createString("deliveryBase");

    public final StringPath deliveryDetail = createString("deliveryDetail");

    public final StringPath deliveryRequest = createString("deliveryRequest");

    public final StringPath howToReceive = createString("howToReceive");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isOrderConfirm = createNumber("isOrderConfirm", Byte.class);

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final StringPath orderEmail = createString("orderEmail");

    public final StringPath orderName = createString("orderName");

    public final StringPath orderNumber = createString("orderNumber");

    public final StringPath orderPhoneNumber = createString("orderPhoneNumber");

    public final NumberPath<Byte> orderStatus = createNumber("orderStatus", Byte.class);

    public final StringPath receiverName = createString("receiverName");

    public final StringPath receiverPhoneNumber = createString("receiverPhoneNumber");

    public final NumberPath<Integer> totalMoney = createNumber("totalMoney", Integer.class);

    public final NumberPath<Long> usersId = createNumber("usersId", Long.class);

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

    public QOrderList(String variable) {
        super(OrderList.class, forVariable(variable));
    }

    public QOrderList(Path<? extends OrderList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderList(PathMetadata metadata) {
        super(OrderList.class, metadata);
    }

}

