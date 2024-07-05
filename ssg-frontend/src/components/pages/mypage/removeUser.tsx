'use client'

import { removeUserAPI } from '@/actions/mypage'
import { useRouter } from 'next/navigation'
export default function RemoveUser() {
    const router = useRouter()
    const removeUser = async () => {
        const confirm = window.confirm('정말 탈퇴하시겠습니까?')
        if (confirm) {
            const res = await removeUserAPI()
            alert('탈퇴되었습니다.')
            router.push('/')
        }
    }

    return (
        <div>
            <button
                className="w-full h-12 text-white font-bold "
                style={{ backgroundColor: '#ff5452', color: '#fff' }}
                onClick={removeUser}
            >
                탈퇴하기
            </button>
        </div>
    )
}
