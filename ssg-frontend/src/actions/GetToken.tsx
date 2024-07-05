'use client'
import { getSession } from 'next-auth/react'

interface tokenType {
    isSuccess: boolean
    message: string
    code: number
    result: string
    iat: number
    exp: number
    jti: string
}

const GetToken = async () => {
    const session = await getSession()
    const token = session?.user?.result || ''
    return token
}

export { GetToken }
