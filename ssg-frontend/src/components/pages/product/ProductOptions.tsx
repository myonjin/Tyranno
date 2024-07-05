'use client'
import Image from 'next/image'
import React, { useEffect, useState } from 'react'
import OptionModal from './OptionModal'
import { useRecoilState, useResetRecoilState, useSetRecoilState } from 'recoil'
import { LastOptionType } from '@/types/LastOptionType'
import { NoOptionItemListAtom, SelectedOptionItemListAtom } from '@/state/SelectedOptionListAtom'
import { CartDataType, ProductDataType } from '@/types/ProductDetailDataType'
import { LastOptionListType } from '@/types/LastOptionType'
import ProductSelect from './ProductSelect'
import { cartClickAPI } from '@/actions/product'
import { useRouter } from 'next/navigation'
import { cartMoneyDataType } from '@/types/CartDataType'
import { CartMoneyAtom } from '@/state/CartCheckedListAtom'
import { signOut, useSession } from 'next-auth/react'
import constraints from '@/actions/constraints'

interface OptionListType {
    idx: number
    name: string
    isChecked: boolean
}

export interface queryKeyType {
    color: string
    size: string
    etc: string
}

export default function ProductOptions({
    isModal,
    productId,
    setIsModal,
    productData,
}: {
    isModal: boolean
    productId: string
    setIsModal: React.Dispatch<React.SetStateAction<boolean>>
    productData: ProductDataType
}) {
    const { data: session } = useSession()
    const [optionData, setOptionData] = useState<LastOptionType[]>([] as LastOptionType[])
    const [newOptionList, setNewOptionList] = useState<OptionListType[]>([] as OptionListType[])
    const [queryUrl, setQueryUrl] = useState<queryKeyType>({
        color: '',
        size: '',
        etc: '',
    } as queryKeyType)
    const [selectedOptionId, setSelectedOptionId] = useState<number>(0)
    const [selectedOptionList, setSelectedOptionList] = useRecoilState(SelectedOptionItemListAtom)
    const [qty, setQty] = useState(1)
    const router = useRouter()
    const resetSelectedOptionList = useResetRecoilState(SelectedOptionItemListAtom)
    const [recoilMoney, setRecoilMoney] = useRecoilState<cartMoneyDataType>(CartMoneyAtom)
    const [noOptionList, setNoOptionList] = useRecoilState(NoOptionItemListAtom)
    const resetNoOptionItemListAtom = useResetRecoilState(NoOptionItemListAtom)

    useEffect(() => {
        return () => {
            resetNoOptionItemListAtom()
        }
    }, [])
    useEffect(() => {
        const getOptionData = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/option/string/${productId}`, {
                cache: 'force-cache',
            })
            if (data) {
                const res = await data.json()
                setOptionData(res.result)
                const newData = res.result.map((opt: string, idx: number) => {
                    return {
                        idx: idx,
                        name: opt,
                        isChecked: idx === 0 ? true : false,
                    }
                })
                setNewOptionList(newData)
                if (newData.length === 0) {
                    const getOptionData1 = async () => {
                        const response = await fetch(`${constraints.Server_Url}/api/v1/option/${productId}`)
                        if (response) {
                            const data = await response.json()

                            if (data.isSuccess) {
                                setNoOptionList([
                                    ...noOptionList,
                                    {
                                        productId: productId,
                                        optionId: data.result[0].optionId,
                                        count: qty,
                                    },
                                ])
                            }
                        }
                    }
                    getOptionData1()
                }
            }
        }
        getOptionData()
    }, [productId])
    // console.log(newOptionList)
    useEffect(() => {
        if (optionData.length > 0) {
            const getOptionDataByOptionId = async () => {
                const res = await fetch(`${constraints.Server_Url}/api/v1/option/names/${selectedOptionId}`, {
                    cache: 'force-cache',
                })
                if (res) {
                    const data = await res.json()
                    // console.log(data)

                    if (data.isSuccess) {
                        setSelectedOptionList([
                            ...selectedOptionList,
                            {
                                productId: productId,
                                optionId: selectedOptionId,
                                productName: productData.productName,
                                price: productData.price,
                                discount: productData.discount,
                                color: data.result?.color,
                                size: data.result?.size,
                                etc: data.result?.additional_option,
                                count: qty,
                            },
                        ])
                    }
                }
            }
            getOptionDataByOptionId()
        }
    }, [selectedOptionId])

    const handleModal = () => {
        setIsModal(false)
    }

    const totalCount = selectedOptionList.reduce((sum: number, item: LastOptionListType) => {
        return sum + item.count
    }, 0)
    // console.log(totalCount)
    const handleCountChange = (newCount: number) => {
        if (newCount >= 1) {
            setQty(newCount)

            // Update the Recoil state `noOptionList` with the new quantity
            setNoOptionList((prevList: any) => {
                const updatedList = prevList.map((item: any) => {
                    if (item.productId === productId) {
                        return {
                            ...item,
                            count: newCount,
                        }
                    }
                    return item
                })
                return updatedList
            })
        }
    }
    const handleCart = async () => {
        if (session?.user.isSuccess) {
            if (newOptionList.length > 0) {
                if (selectedOptionId > 0) {
                    for (let i = 0; i < selectedOptionList.length; i++) {
                        const data: CartDataType = {
                            optionId: selectedOptionList[i].optionId,
                            count: selectedOptionList[i].count,
                        }
                        const res = await cartClickAPI(data)
                    }
                    resetSelectedOptionList()
                    router.push('/cart')
                } else {
                    alert('상품 옵션을 선택해주세요')
                }
            } else {
                const data: CartDataType = {
                    optionId: noOptionList[0].optionId,
                    count: noOptionList[0].count,
                }
                const res = await cartClickAPI(data)

                resetSelectedOptionList()
                router.push('/cart')
            }
        } else {
            alert('로그인 후 이용해주세요')
            router.push(`/user/login`)
        }
    }

    const handleOrder = async () => {
        if (session?.user.isSuccess) {
            if (newOptionList.length > 0) {
                if (selectedOptionId > 0) {
                    const orderMoney = {
                        orderMoney: productData.price * qty,
                        deliveryMoney: 3000,
                        discountMoney: -(productData.price * qty * (productData.discount / 100)),
                    }
                    setRecoilMoney(orderMoney)
                    router.push('/order')
                } else {
                    alert('상품 옵션을 선택해주세요')
                }
            } else {
                const orderMoney = {
                    orderMoney: productData.price * qty,
                    deliveryMoney: 3000,
                    discountMoney: -(productData.price * qty * (productData.discount / 100)),
                }
                setRecoilMoney(orderMoney)
                router.push('/order')
            }
        } else {
            alert('로그인 후 이용해주세요')
            router.push(`/user/login`)
        }
    }
    // console.log(selectedOptionId)

    return (
        <>
            <div
                className={`${
                    isModal ? 'bottom-8 ease-in-out ' : '-bottom-[400px] easy-out-in'
                } fixed transition-all delay-150 z-[10] w-full `}
            >
                <div
                    className=" bg-white  rounded-t-xl min-h-[200px]"
                    style={{ boxShadow: '0px -4px 10px 0px rgba(0, 0, 0, 0.1)' }}
                >
                    <div className=" w-full h-5 p-4 flex items-center justify-center mb-2 " onClick={handleModal}>
                        <Image
                            width={20}
                            height={20}
                            src="https://img.icons8.com/ios-glyphs/30/back.png"
                            alt="back"
                            style={{ transform: 'rotate(270deg)' }}
                        />
                    </div>

                    {newOptionList &&
                        newOptionList.map((item: OptionListType, index) => (
                            <OptionSelecter
                                key={index}
                                item={item}
                                productId={productId}
                                setNewOptionList={setNewOptionList}
                                newOptionList={newOptionList}
                                queryUrl={queryUrl}
                                setQueryUrl={setQueryUrl}
                                productData={productData}
                                selectedOptionId={selectedOptionId}
                                setSelectedOptionId={setSelectedOptionId}
                                selectedOptionList={selectedOptionList}
                            />
                        ))}

                    {newOptionList.length === 0 && (
                        <div className="p-3">
                            <div className="mt-5 border py-2  w-full bg-gray-100  border-black rounded-md min-h-[100px] ">
                                <div className="flex text-sm ml-2">{productData.productName}</div>
                                <div className="absolute ml-2 bg-white mt-2 w-20 flex items-center justify-center h-8">
                                    <button
                                        className="text-4xl font-thin mb-2"
                                        onClick={() => handleCountChange(qty - 1)}
                                    >
                                        -
                                    </button>
                                    <span className="mx-3">{qty}</span>
                                    <button
                                        className="text-4xl font-thin mb-2"
                                        onClick={() => handleCountChange(qty + 1)}
                                    >
                                        +
                                    </button>
                                </div>
                                <div className=" absolute  right-5 text-lg font-semibold mt-5">
                                    {Math.floor(
                                        productData.price * (1 - productData.discount / 100) * qty,
                                    ).toLocaleString()}
                                    원
                                </div>
                            </div>
                        </div>
                    )}
                    <div className="flex justify-end py-6 p-2">
                        <p className="mr-2 font-bold">총 합계</p>

                        <div className=" text-red-500 font-bold  text-xl">
                            {totalCount === 0 ? (
                                <p>
                                    {Math.floor(
                                        productData.price * (1 - productData.discount / 100) * qty,
                                    ).toLocaleString()}
                                    원
                                </p>
                            ) : (
                                <p>
                                    {Math.floor(
                                        productData.price * (1 - productData.discount / 100) * totalCount,
                                    ).toLocaleString()}
                                    원
                                </p>
                            )}
                        </div>
                    </div>
                </div>
            </div>
            <div
                className={`${
                    isModal ? 'bottom-0 z-[10]' : '-bottom-[200px] delay-300 '
                } transition-all flex items-center h-12 fixed w-full`}
            >
                <button
                    onClick={() => {
                        handleCart()
                    }}
                    className="flex justify-center items-center bg-black flex-grow h-12"
                >
                    <span className="  text-white">장바구니</span>
                </button>

                <button
                    onClick={() => {
                        handleOrder()
                    }}
                    className="flex justify-center items-center bg-red-500  h-12 flex-grow"
                >
                    <span className="  text-white">바로구매</span>
                </button>
            </div>
        </>
    )
}

const OptionSelecter = ({
    item,
    productId,
    setNewOptionList,
    newOptionList,
    queryUrl,
    setQueryUrl,
    productData,
    selectedOptionId,
    setSelectedOptionId,
    selectedOptionList,
}: {
    item: OptionListType
    productId: string
    newOptionList: OptionListType[]
    setNewOptionList: React.Dispatch<React.SetStateAction<OptionListType[]>>
    queryUrl: queryKeyType
    setQueryUrl: React.Dispatch<React.SetStateAction<queryKeyType>>
    productData: ProductDataType
    selectedOptionId: number
    setSelectedOptionId: React.Dispatch<React.SetStateAction<number>>
    selectedOptionList: LastOptionListType[]
}) => {
    const [selectedOption, setSelectedOption] = useState<string>(`선택하세요. (${item.name})`)
    const [showModal, setShowModal] = useState<boolean>(false)
    const resetSelectedOptionList = useResetRecoilState(SelectedOptionItemListAtom)

    useEffect(() => {
        return () => {
            resetSelectedOptionList()
        }
    }, [])
    useEffect(() => {
        setNewOptionList(
            newOptionList.map((opt: OptionListType) => {
                if (opt.idx === item.idx + 1 && opt.idx !== newOptionList.length - 1) {
                    return {
                        ...opt,
                        isChecked: true,
                    }
                } else if (opt.idx === item.idx + 1 && opt.idx === newOptionList.length - 1) {
                    return {
                        ...opt,
                        isChecked: true,
                    }
                }
                return opt
            }),
        )
    }, [selectedOption])

    return (
        <>
            <div className="px-3 py-1">
                <div
                    className={`${
                        item.isChecked ? '' : 'opacity-30 cursor-not-allowed'
                    }w-full border bg-white rounded-md  py-2 `}
                    onClick={item.isChecked ? () => setShowModal(true) : () => alert('선택할 수 없습니다.')}
                >
                    <div className="ml-2 text-sm ">{selectedOption}</div>
                </div>
            </div>
            {item.idx >= newOptionList.length - 1 &&
                selectedOptionList.map((item: LastOptionListType, idx: number) => (
                    <div key={idx} className="p-3">
                        <ProductSelect item={item} />
                    </div>
                ))}
            <OptionModal
                last={item.idx >= newOptionList.length - 1 ? true : false}
                showModal={showModal}
                optionType={item.name}
                setShowModal={setShowModal}
                setSelectedOption={setSelectedOption}
                productId={productId}
                queryUrl={queryUrl}
                setQueryUrl={setQueryUrl}
                setSelectedOptionId={setSelectedOptionId}
                selectedOptionId={selectedOptionId}
            />
        </>
    )
}
