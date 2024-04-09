import { changePasswordAPI } from '@/actions/user'
import { ChangeInfo } from '@/types/MyInfoDataType'
import { DeleteAPI, GetAPI, PostAPI, PutAPI } from './FetchAPI'
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
async function mchangePasswordAPI(newPassword: string) {
    const response = await PutAPI('/api/v1/users/modify-pw', { newPassword: newPassword }, await token)
    return response.result
}
async function PointAgreeAPI(isAgree: number) {
    const response = await PutAPI('/api/v1/users/shinsegae-marketing', { isAgree: isAgree }, await token)
    return response.result
}
async function ssgAgreeAPI(isAgree: number) {
    const response = await PutAPI('/api/v1/users/ssg-marketing', { isAgree: isAgree }, await token)
    return response.result
}

export {
    getMyInfo,
    getMynameAPI,
    submitChangeInfoAPI,
    getOrderListAPI,
    removeUserAPI,
    mchangePasswordAPI,
    PointAgreeAPI,
    ssgAgreeAPI,
}
