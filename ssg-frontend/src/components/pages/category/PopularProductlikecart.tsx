'use client'
import Image from 'next/image'
import { useEffect, useState } from 'react'
import HeartIcon from '@/images/HeartIcon.png'
import RedHeartIcon from '@/images/RedHeartIcon.png'
import { LikeClickAPI, getLikeAPI, getProductListAPI } from '@/actions/product'
import { LIKEType } from '@/types/LikeType'
import { useRouter } from 'next/navigation'
import { cartClickAPI } from '@/actions/category'

function LikeAndCart({ productId, islike }: { productId: string; islike: number }) {
    const [like, setLike] = useState<number>(99)

    const handleLike = async () => {
        const body: LIKEType = {
            productId: productId,
        }
        const res = await LikeClickAPI(body)
        const response = await getLikeAPI(productId)
        setLike(response.result)

        // setLike(response)
    }
    useEffect(() => {
        const getLike = async () => {
            const response = await getLikeAPI(productId)
            setLike(response.result)
        }
        getLike()
    }, [productId])
    const router = useRouter()
    const handleNonUser = async () => {
        alert('로그인 후 이용해주세요.')
        router.push(`/user/login`)
    }
    const handleCart = async () => {
        const response = await cartClickAPI(productId)
        if (response.isSuccess) {
            if (response.result === '상품 상세에서 옵션을 선택해주세요.') {
                alert('이 상품은 옵션이 있는 상품 입니다.\n상품상세에서 옵션을 선택해주세요.')
                router.push(`/product/${productId}`)
            } else {
                alert('장바구니에 상품을 담았습니다.')
            }
        } else {
            alert('로그인 후 이용해주세요.')
            router.push(`/user/login`)
        }
        console.log(response.result, '장바구니 요청성공?')
    }

    return (
        <div className=" flex items-center pt-[0.125rem] pb-[0.125rem] ">
            <div className="flex-grow flex-shrink basis-[0%] self-stretch justify-self-stretch"></div>

            {like === 99 ? (
                <button onClick={() => handleLike()} className=" flex justify-center items-center  w-[20px] h-[20px]">
                    <Image src={HeartIcon} alt="안좋아요"></Image>
                </button>
            ) : like === 11 ? (
                <button onClick={() => handleLike()} className=" flex justify-center items-center w-[20px] h-[20px]">
                    <Image src={RedHeartIcon} alt="좋아요" />
                </button>
            ) : like === null ? (
                <button
                    onClick={() => handleNonUser()}
                    className=" flex justify-center items-center  w-[20px] h-[20px]"
                >
                    <Image src={HeartIcon} alt="안좋아요"></Image>
                </button>
            ) : (
                ''
            )}
            <button onClick={() => handleCart()} className="inline-flex justify-center items-center w-7 h-7 ml-1">
                <Image
                    width="24"
                    height="48"
                    src="https://img.icons8.com/parakeet-line/48/shopping-cart.png"
                    alt="장바구니"
                />
            </button>
        </div>
    )
}
export default LikeAndCart
