package com.tyranno.ssg.cart.application;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.cart.dto.Request.*;
import com.tyranno.ssg.cart.dto.Response.CartListDto;
import com.tyranno.ssg.cart.infrastructure.CartRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.application.OptionService;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.product.infrastructure.DiscountRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;
    private final VendorProductRepository vendorProductRepository;
    private final ProductThumRepository productThumRepository;
    private final DiscountRepository discountRepository;
    private final OptionService optionService;

    @Transactional
    @Override
    public String addCartByOption(CartAddDto cartAddDto, String uuid) {
        Option option = optionRepository.findById(cartAddDto.getOptionId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));

        return addCart(uuid, option, cartAddDto.getCount());
    }

    @Transactional
    @Override
    public String addCartByProduct(Long productId, String uuid) {
        // 옵션이 있는 상품인 경우, 바로 추가되지 않고 상품 상세페이지로 이동
        if (!optionService.getOptionAble(productId).isEmpty()) return "상품 상세에서 옵션을 선택해주세요.";

        Option option = optionRepository.findByProductId(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));

        return addCart(uuid, option, 1); // 상품리스트에선 1개씩만 추가 가능

    }

    @Transactional
    @Override
    public String addCart(String uuid, Option option, int count) {
        Users users = usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));

        // 이미 추가할려는 옵션이 장바구니에 존재
        if (cartRepository.existsByOptionAndUsers(option, users)) {
            Cart cart = cartRepository.findByOptionAndUsers(option, users);

            cart = Cart.builder()
                    .id(cart.getId())
                    .users(cart.getUsers())
                    .option(cart.getOption())
                    .count(cart.getCount() + count) // 이미 담겨 있던 상품에 수량만 추가
                    .isKeep(cart.getIsKeep())
                    .build();

            cartRepository.save(cart);
            return "이미 있는 제품을 다시 담았습니다.";
        }
        // 새로운 상품의 옵션을 장바구니에 담았을 때
        Cart cart = Cart.builder()
                .users(users)
                .option(option)
                .count(count)
                .isKeep((byte) 99) // 처음 담겼을 땐, 계속 담아두기 여부 false
                .build();

        cartRepository.save(cart);
        return "새로운 품목이 장바구니에 담겼습니다.";
    }

    @Override
    public List<CartListDto> getCartList(String uuid) {
        List<Cart> carts = cartRepository.findByUsersUuid(uuid);

        return carts.stream().map(cart -> {
            Long productId = cart.getOption().getProduct().getId();
            String imageUrl = productThumRepository.findByProductIdAndPriority(productId, 1)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM))
                    .getImageUrl();
            int discount = discountRepository.findByProductId(productId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DISCOUNT))
                    .getDiscount();
            String vendorName = vendorProductRepository.findByProductId(productId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_VENDORPRODUCT))
                    .getVendor().getVendorName();

            return CartListDto.FromEntity(cart, imageUrl, discount, vendorName);
        }).toList();
    }

    @Transactional
    @Override
    public void deleteCartList(List<CartIdDto> cartDeleteList) {
        List<Long> cartIdList = cartDeleteList.stream().map(CartIdDto::getCartId).toList();
        cartRepository.deleteByIdIn(cartIdList);
    }

    @Transactional
    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Transactional
    @Override
    public void modifyCount(CartCountModifyDto cartCountModifyDto) {
        Cart cart = getCart(cartCountModifyDto.getCartId());
        cartRepository.save(cartCountModifyDto.toEntity(cart));
    }

    @Transactional
    @Override
    public void modifyOption(CartOptionModifyDto cartOptionModifyDto) {
        Cart cart = getCart(cartOptionModifyDto.getCartId());
        Option option = optionRepository.findById(cartOptionModifyDto.getOptionId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));

        cartRepository.save(cartOptionModifyDto.toEntity(cart, option));
    }

    @Override
    public int getUsersCartCount(String uuid) {
        return cartRepository.countByUserUuid(uuid);
    }

    @Override
    public void modifyCartIsKeep(CartKeepModifyDto cartKeepModifyDto) {
        Cart cart = getCart(cartKeepModifyDto.getCartId());
        cartRepository.save(cartKeepModifyDto.toEntity(cart));
    }

    private Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_CART));
    }
}
