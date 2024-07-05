import NextAuth from 'next-auth/next'

declare module 'next-auth' {
    interface Session {
        user: {
            isSuccess: boolean
            message: string
            code: number
            result: string
        }
    }
}
s
