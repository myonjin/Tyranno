import ClipHeader from '@/components/pages/clip/ClipHeader'
import ClipMain from '@/components/pages/clip/ClipMain'
import ClipNavbar from '@/components/pages/clip/ClipNavbar'
import React from 'react'


export default async function ClipPage() {
    //memberId는 쿠키 또는 세션에서 가져오기
    // -> use client 써야할경우 데이터 페칭 함수를 각 컴포넌트 안으로 넣기


    //Header 추가하기
    return (
        <>
            <ClipHeader />
            <ClipNavbar  />
            <ClipMain />
        </>
    )
}
