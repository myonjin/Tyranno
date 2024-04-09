import { ChangeInfo } from '@/types/MyInfoDataType'
import { DeleteAPI, GetAPI, PutAPI } from './FetchAPI'
import { GetToken } from './GetToken'
const token = GetToken()
async function getMyInfo() {
    const response = await GetAPI('/api/v1/users', undefined, await token)
    return response.result
}
async function getMynameAPI() {
    const response = await GetAPI('/api/v1/users/name', undefined, await token)
    return response.result
}
async function submitChangeInfoAPI(data: ChangeInfo) {
    const response = await PutAPI('/api/v1/users', data, await token)
    return response.result
}
async function getOrderListAPI() {
    const response = await GetAPI('/api/v1/order', undefined, await token)
    return response.result
}
async function removeUserAPI() {
    const response = await DeleteAPI('/api/v1/users', undefined, await token)
    return response.result
}
export { getMyInfo, getMynameAPI, submitChangeInfoAPI, getOrderListAPI, removeUserAPI }
