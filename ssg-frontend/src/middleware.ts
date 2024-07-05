import { NextResponse, type NextRequest } from 'next/server'
import { getSession } from 'next-auth/react'
// import middleware from 'next-auth/middleware'

export { default } from 'next-auth/middleware'

export async function middleware(request: NextRequest) {
    let cookie = request.cookies.get('__Secure-next-auth.session-token')

    if (cookie === undefined) {
        if (request.nextUrl.pathname.startsWith('/mypage')) {
            const redirectUrl = new URL('/user/login', request.nextUrl.origin)
            return NextResponse.redirect(redirectUrl.toString())
        }
        if (request.nextUrl.pathname.startsWith('/recentproduct')) {
            const redirectUrl = new URL('/user/login', request.nextUrl.origin)
            return NextResponse.redirect(redirectUrl.toString())
        }
        if (request.nextUrl.pathname.startsWith('/order')) {
            const redirectUrl = new URL('/user/login', request.nextUrl.origin)
            return NextResponse.redirect(redirectUrl.toString())
        }
        if (request.nextUrl.pathname.startsWith('/cart')) {
            const redirectUrl = new URL('/user/login', request.nextUrl.origin)
            return NextResponse.redirect(redirectUrl.toString())
        }
    } else {
        if (request.nextUrl.pathname.startsWith('/user')) {
            // const redirectUrl = new URL('/mypage', request.nextUrl.origin)
            // return NextResponse.redirect(redirectUrl.toString())
            return NextResponse.redirect(new URL('/mypage', request.url))
        }
    }
}
