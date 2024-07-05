import { KakaoPayDataType, OrderFormDataType } from '@/types/OrderDataTypte'
import { GetAPI, PostAPI } from './FetchAPI'
import { GetToken } from './GetToken'
const token = GetToken()
async function getItemsOrderAPI(productId: number) {
    const response = await GetAPI(`/api/v1/product/productInformation/${productId}`, undefined, await token)
    return response.result
}
async function getDeliveryAddressAPI() {
    const response = await GetAPI('/api/v1/delivery/order', undefined, await token)
    return response.result
}

async function getOptionListAPI(optionId: number) {
    const response = await GetAPI(`/api/v1/option/names/${optionId}`, undefined, await token)
    return response.result
}
async function orderComplete(data: OrderFormDataType) {
    const response = await PostAPI('/api/v1/order', data, await token)
    return response
}
async function kakaoPayReadyAPI(data: KakaoPayDataType) {
    const response = await PostAPI('/api/v1/pay/ready', data)
    return response.result
}
export { getItemsOrderAPI, getDeliveryAddressAPI, getOptionListAPI, orderComplete, kakaoPayReadyAPI }
