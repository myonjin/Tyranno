export interface OrderFormDataType {
    optionIdList: OrderOptionFormDataType[]
    deliveryRequest: string
    deliveryBase: string
    deliveryDetail: string
    zipCode: number
    receiverName: string
    receiverPhoneNumber: string
    orderName: string
    orderPhoneNumber: string
    orderEmail: string
    totalMoney: number
}
interface OrderOptionFormDataType {
    optionId: number
    count: number
    money: number //할인된 금액
}

export interface KakaoPayDataType {
    item_name: string
    total_amount: number
}
