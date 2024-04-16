'use client'

import { getMyInfo } from '@/actions/mypage'
import { MyInfo } from '@/types/MyInfoDataType'
import { useEffect, useState } from 'react'

export default function SsgAgreeInfo() {
    const [myInfo, setMyInfo] = useState<MyInfo>()
    const getMyInfos = async () => {
        const response = await getMyInfo()
        setMyInfo(response)
    }

    useEffect(() => {
        getMyInfos()
    }, [])
    return (
        <>
            <div style={{ backgroundColor: '#f8f8f8', padding: '15px 20px' }}>
                <h3> 회원 기본 정보</h3>
            </div>
            <div className="p-4">
                <div className="flex py-4">
                    <span className="w-20">아이디</span>
                    <span>{myInfo?.loginId}</span>
                </div>
                <hr />
                <div className="flex py-4">
                    <span className="w-20">이메일</span>
                    <span>{myInfo?.email}</span>
                </div>
                <hr />
                <span className="text-sm ">
                    ※ 이메일주소 및 휴대폰번호는 'MY SSG - 회원정보 변경' 메뉴에서 변경하실 수 있습니다.
                </span>
            </div>
        </>
    )
}
