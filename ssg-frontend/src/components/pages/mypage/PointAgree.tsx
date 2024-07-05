'use client'

import { PointAgreeAPI } from '@/actions/mypage'
import { useState } from 'react'

export default function PointAgree() {
    const [agree, setAgree] = useState<number>(99)
    const handleChange = async () => {
        if (agree === 11) {
            setAgree(99)
        } else {
            setAgree(11)
        }
    }
    const handleSubmit = async () => {
        await PointAgreeAPI(agree)
        alert('변경되었습니다')
    }

    return (
        <section>
            <label className="flex p-3">
                <input type="checkbox" className="w-[18px] h-[18px] accent-red-500 ml-1" onChange={handleChange} />
                <span className="ml-2 tracking-[-0.3px] text-xs">
                    <span className="font-bold">(선택)</span>
                    신세계포인트(이마트·신세계백화점) ↔ SSG.COM 개인정보 제3자 제공 동의
                </span>
            </label>
            <div className="p-4" onClick={handleSubmit}>
                <button className="w-full p-4 text-center font-normal border border-black">변경하기</button>
            </div>
        </section>
    )
}
