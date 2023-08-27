package com.room.yeahnolja.domain.hotel.controller;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hotel Controller")
@RestController
@RequiredArgsConstructor
public class HotelController {
    /**
     * TODO:
     * private final 설정으로 객체 사용을 하고 있으니 생성자 주입방식임을 알 수 있다.
     * 따라서, HotelController 객체가 생성될 때, hotelService에 주입될 수 있도록 설정해줘야한다.
     * 그래서! 직접 생성자를 넣을 수도 있지만,
     * final이나 @NonNull로 선언된 필드만을 파라미터로 갖는 생성자를 자동으로 생성해주는
     *
     * @RequiredArgsConstructor를 사용한다.
     */
    private final HotelService hotelService;

    @Operation(summary = "호텔 단건 등록 (mybatis 사용)")
    @PostMapping("/hotel")
    public ResponseEntity<Void> saveHotel(@RequestBody HotelRequestDto requestDto) {
        boolean isSaved = hotelService.saveHotel(requestDto);
        return isSaved ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "호텔 단건 등록 (jpa 사용)")
    @PostMapping("/hotel/save")
    public void saveHotel(@RequestBody Hotel hotel) {
        hotelService.save(hotel);
    }

    @Operation(summary = "호텔 전체 조회")
    @GetMapping("/hotels")
    public List<HotelResponseDto> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "호텔 단건 조회")
    @GetMapping("/hotels/{hotelId}")
    public HotelResponseDto getHotel(@PathVariable int hotelId) {
        return hotelService.getHotel(hotelId);
    }

    @Operation(summary = "특정 지역에 대한 호텔 조회")
    @GetMapping("/hotels/location")
    public List<HotelResponseDto> getHotelsByLocation(@RequestParam String location) {
        return hotelService.getHotelsByLocation(location);
    }

    @Operation(summary = "특정 가격에 대한 호텔 조회")
    @GetMapping("/hotels/price")
    public List<HotelResponseDto> getHotelsByPrice(@RequestParam int price) {
        return hotelService.getHotelsByPrice(price);
    }

    @Operation(summary = "호텔 단건 수정")
    @PatchMapping("/hotel/{hotelId}")
    public void modifyHotel(@PathVariable int hotelId, @RequestBody HotelRequestDto requestDto) {
        hotelService.modifyHotel(hotelId, requestDto);
    }

    @Operation(summary = "호텔 단건 삭제")
    @DeleteMapping("/hotel/{hotelId}")
    public void removeHotel(@PathVariable int hotelId) {
        hotelService.removeHotel(hotelId);
    }

    @Operation(summary = "특정 호텔명에 대한 호텔 조회")
    @GetMapping("/hotels/name")
    public List<HotelResponseDto> getHotelsByName(@RequestParam String name) {
        return hotelService.getHotelsByName(name);
    }
}