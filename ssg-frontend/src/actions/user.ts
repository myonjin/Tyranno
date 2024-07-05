import { FindUserDataType } from '../types/FindUserDataType'
import { UserDataType, authCode } from '@/types/UserDataType'
import { PostAPI, PutAPI } from './FetchAPI'
import { ChangePasswordDataType } from '@/types/ChangePassword'

async function validLoginId(loginId: string) {
    const response = await PostAPI('/api/v1/auth/id-check', { loginId: loginId })
    return response
}
async function signupAPI(requestData: UserDataType) {
    const response = await PostAPI(`/api/v1/auth/signup`, requestData)
    return response
}
async function findIdAPI(phoneNumber: string) {
    const response = await PostAPI('/api/v1/auth/find-id', { phoneNumber: phoneNumber })
    return response
}

async function changePasswordAPI(idPW: ChangePasswordDataType) {
    const response = await PutAPI('/api/v1/auth/change_pw', idPW)
    return response
}
async function sendTextAPI(phoneNumber: string) {
    const response = await PostAPI('/api/v1/auth/sms/send', { phoneNumber: phoneNumber })
    return response
}
async function checkAuthCodeAPI(verifyCode: authCode) {
    const response = await PostAPI('/api/v1/auth/sms/verify', verifyCode)
    return response
}
async function checkUserAPI(phoneNumber: string) {
    const response = await PostAPI('/api/v1/auth/check', { phoneNumber: phoneNumber })
    return response
}
async function validEmailAPI(email: string) {
    const response = await PostAPI('/api/v1/auth/email-check', { email: email })
    return response
}
export {
    signupAPI,
    validLoginId,
    findIdAPI,
    changePasswordAPI,
    sendTextAPI,
    checkAuthCodeAPI,
    checkUserAPI,
    validEmailAPI,
}
